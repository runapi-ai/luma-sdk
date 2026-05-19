import type { AsyncTaskStatus } from '@runapi.ai/core';

export interface ModifyVideoParams {
  prompt: string;
  video_url: string;
  callback_url?: string;
  watermark?: string;
}

export interface TaskCreateResponse {
  id: string;
  status?: AsyncTaskStatus;
}

export interface Video {
  url: string;
}

export interface ModifyVideoResponse {
  id: string;
  status: AsyncTaskStatus;
  videos?: Video[];
  sources?: Video[];
  error?: string;
  [key: string]: unknown;
}

export type CompletedModifyVideoResponse = ModifyVideoResponse & {
  status: 'completed';
  videos: Video[];
};
