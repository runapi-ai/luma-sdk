"""Luma client."""

from __future__ import annotations

from typing import Any, Optional

from runapi.core import ProviderClient

from .resources.modify_video import ModifyVideo


class LumaClient(ProviderClient):
    """Luma modify-video client.

    Example::

        client = LumaClient(api_key="sk-...")
        result = client.modify_video.run(
            model="...", prompt="Make it cinematic",
            source_video_url="https://cdn.runapi.ai/public/samples/video.mp4",
        )
    """

    def __init__(self, api_key: Optional[str] = None, **options: Any) -> None:
        super().__init__(api_key, **options)
        http = self._http
        self.modify_video = ModifyVideo(http)
