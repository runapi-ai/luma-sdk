"""Luma client."""

from __future__ import annotations

from typing import Any, Optional

from runapi.core import ClientOptions, HttpClient, resolve_api_key

from .resources.modify_video import ModifyVideo


class LumaClient:
    """Luma modify-video client.

    Example::

        client = LumaClient(api_key="sk-...")
        result = client.modify_video.run(
            model="...", prompt="Make it cinematic",
            source_video_url="https://example.com/source.mp4",
        )
    """

    def __init__(self, api_key: Optional[str] = None, **options: Any) -> None:
        resolved_api_key = resolve_api_key(api_key)
        client_options = ClientOptions(api_key=resolved_api_key, **options)
        http = client_options.http_client or HttpClient(client_options)
        self.modify_video = ModifyVideo(http)
