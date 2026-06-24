"""Luma modify-video resource."""

from __future__ import annotations

from typing import Any

from runapi.core import Resource

from ..contract_gen import CONTRACT
from ..types import CompletedModifyVideoResponse, ModifyVideoResponse


class ModifyVideo(Resource):
    """Modify a source video with Luma."""

    ENDPOINT = "/api/v1/luma/modify_video"

    RESPONSE_CLASS = ModifyVideoResponse
    COMPLETED_RESPONSE_CLASS = CompletedModifyVideoResponse

    def run(self, **params: Any) -> Any:
        """Create a task and poll until it completes."""
        task = self.create(**params)
        return self._poll_until_complete(lambda: self.get(task.id))

    def create(self, **params: Any) -> Any:
        """Create a modify-video task and return immediately with an ``id``."""
        compacted = self._compact_params(params)
        self._validate_contract(CONTRACT["modify-video"], compacted)
        return self._request("post", self.ENDPOINT, body=compacted)

    def get(self, id: str) -> Any:
        """Fetch the current status of a modify-video task."""
        return self._request("get", f"{self.ENDPOINT}/{id}")
