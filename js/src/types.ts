import type { AsyncTaskStatus } from '@runapi.ai/core';

/**
 * Parameters for prompt-guided video modification. The prompt must be in English.
 * Source motion is preserved while the described visual changes are applied.
 */
export interface ModifyVideoParams {
  /** Model slug. */
  model: string;
  /** English-language description of the desired visual change (e.g. "Add a dramatic sunset lighting effect"). */
  prompt: string;
  /** Publicly accessible URL of the source video to modify. */
  source_video_url: string;
  /** URL to receive a webhook notification when the task completes. */
  callback_url?: string;
  /** Watermark mode for the output video. */
  watermark?: string;
}

/** Initial response when a video modification task is created. */
export interface TaskCreateResponse {
  id: string;
  status?: AsyncTaskStatus;
}

/** A video file with a download URL. */
export interface Video {
  url: string;
}

/**
 * Task status response for a video modification operation.
 * On completion, {@link videos} contains the modified output and {@link sources} contains the original input.
 */
export interface ModifyVideoResponse {
  id: string;
  status: AsyncTaskStatus;
  /** Modified output video(s), populated when the task completes. */
  videos?: Video[];
  /** Original source video(s) provided as input. */
  sources?: Video[];
  /** Human-readable error description when the task fails. */
  error?: string;
  [key: string]: unknown;
}

/** Completed video modification response with guaranteed output videos. */
export type CompletedModifyVideoResponse = ModifyVideoResponse & {
  status: 'completed';
  videos: Video[];
};
