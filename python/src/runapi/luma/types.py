"""Luma response models."""

from __future__ import annotations

from runapi.core import BaseModel, TaskResponse, optional, required


class Video(BaseModel):
    """A generated or source video reference."""

    url = optional(str)


class ModifyVideoResponse(TaskResponse):
    """Response for a modify-video task."""

    id = required(str)
    status = optional(str, enum=lambda: TaskResponse.Status.ALL)
    videos = optional([lambda: Video])
    sources = optional([lambda: Video])
    error = optional(str)


class CompletedModifyVideoResponse(ModifyVideoResponse):
    """Returned by ``modify_video.run()`` once polling observes completion.

    ``videos`` is required so callers never have to null-check it on success.
    """

    videos = required([lambda: Video])
