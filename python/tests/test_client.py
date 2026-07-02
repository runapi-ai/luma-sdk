import pytest

from runapi.core import config
from runapi.core.errors import AuthenticationError, ValidationError
from runapi.luma import LumaClient
from runapi.luma.resources.modify_video import ModifyVideo
from runapi.luma.types import CompletedModifyVideoResponse, ModifyVideoResponse


class FakeHttp:
    """Records (method, path, body) and replays preset responses by call order."""

    def __init__(self, *responses):
        self._responses = list(responses)
        self.calls = []

    def request(self, method, path, body=None, options=None):
        self.calls.append((method, path, body))
        if self._responses:
            return self._responses.pop(0)
        return {"id": "task_1", "status": "pending"}


@pytest.fixture(autouse=True)
def reset_config(monkeypatch):
    monkeypatch.delenv("RUNAPI_API_KEY", raising=False)
    monkeypatch.setattr(config, "api_key", None)
    yield


# --- authentication -------------------------------------------------------


def test_accepts_api_key_parameter():
    assert isinstance(LumaClient(api_key="param-key", http_client=FakeHttp()), LumaClient)


def test_falls_back_to_global(monkeypatch):
    monkeypatch.setattr(config, "api_key", "global-key")
    assert isinstance(LumaClient(http_client=FakeHttp()), LumaClient)


def test_falls_back_to_env(monkeypatch):
    monkeypatch.setenv("RUNAPI_API_KEY", "env-key")
    assert isinstance(LumaClient(http_client=FakeHttp()), LumaClient)


def test_raises_without_api_key():
    with pytest.raises(AuthenticationError, match="API key is required"):
        LumaClient()


# --- transport injection / accessors --------------------------------------


def test_uses_injected_http_client():
    fake = FakeHttp()
    client = LumaClient(api_key="k", http_client=fake)
    assert client.modify_video._http is fake


def test_exposes_resource_accessors():
    client = LumaClient(api_key="k", http_client=FakeHttp())
    assert isinstance(client.modify_video, ModifyVideo)


# --- request shapes -------------------------------------------------------


def test_create_posts_compacted_body():
    fake = FakeHttp({"id": "t1", "status": "pending"})
    client = LumaClient(api_key="k", http_client=fake)
    result = client.modify_video.create(
        model="luma-modify-video",
        prompt="hello",
        source_video_url="https://cdn.runapi.ai/public/samples/video.mp4",
        callback_url=None,
    )
    assert fake.calls == [
        (
            "post",
            "/api/v1/luma/modify_video",
            {
                "model": "luma-modify-video",
                "prompt": "hello",
                "source_video_url": "https://cdn.runapi.ai/public/samples/video.mp4",
            },
        ),
    ]
    assert isinstance(result, ModifyVideoResponse)
    assert result.id == "t1"


def test_get_fetches_by_id():
    fake = FakeHttp({"id": "t1", "status": "processing"})
    client = LumaClient(api_key="k", http_client=fake)
    client.modify_video.get("t1")
    assert fake.calls == [("get", "/api/v1/luma/modify_video/t1", None)]


def test_run_polls_and_narrows_completed_type():
    fake = FakeHttp(
        {"id": "t1", "status": "pending"},
        {
            "id": "t1",
            "status": "completed",
            "videos": [{"url": "https://x/y.mp4"}],
        },
    )
    client = LumaClient(api_key="k", http_client=fake)
    result = client.modify_video.run(
        model="luma-modify-video",
        prompt="hi",
        source_video_url="https://cdn.runapi.ai/public/samples/video.mp4",
    )

    assert isinstance(result, CompletedModifyVideoResponse)
    assert result.videos[0].url == "https://x/y.mp4"
    assert [call[0] for call in fake.calls] == ["post", "get"]


# --- validation -----------------------------------------------------------


def test_create_requires_model():
    client = LumaClient(api_key="k", http_client=FakeHttp())
    with pytest.raises(ValidationError, match="model must be one of: luma-modify-video"):
        client.modify_video.create(prompt="hi", source_video_url="https://cdn.runapi.ai/public/samples/source.mp4")


def test_create_requires_prompt():
    client = LumaClient(api_key="k", http_client=FakeHttp())
    with pytest.raises(ValidationError, match="prompt is required"):
        client.modify_video.create(model="luma-modify-video", source_video_url="https://cdn.runapi.ai/public/samples/source.mp4")


def test_create_requires_source_video_url():
    client = LumaClient(api_key="k", http_client=FakeHttp())
    with pytest.raises(ValidationError, match="source_video_url is required"):
        client.modify_video.create(model="luma-modify-video", prompt="hi")
