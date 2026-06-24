import type { HttpClient, PollingOptions, RequestOptions, ActionSchema } from '@runapi.ai/core';
import { compactParams, validateParams } from '@runapi.ai/core';
import { pollUntilComplete } from '@runapi.ai/core/internal';
import { contract } from '../contract_gen';
import type {
  CompletedModifyVideoResponse,
  TaskCreateResponse,
  ModifyVideoParams,
  ModifyVideoResponse,
} from '../types';

const ENDPOINT = '/api/v1/luma/modify_video';

/** Apply prompt-guided edits to an existing video. The source video's motion is preserved while visual changes described in the prompt are applied. */
export class ModifyVideo {
  constructor(private readonly http: HttpClient) {}

  /**
   * Apply prompt-guided visual edits to an existing video while preserving its motion and wait until complete.
   * @param params Modification parameters.
   * @param options Per-request and polling overrides.
   * @returns The completed task with videos.
   */
  async run(
    params: ModifyVideoParams,
    options?: RequestOptions & PollingOptions,
  ): Promise<CompletedModifyVideoResponse> {
    const { id } = await this.create(params, options);
    const response = await pollUntilComplete<ModifyVideoResponse>(() => this.get(id, options), {
      maxWaitMs: options?.maxWaitMs,
      pollIntervalMs: options?.pollIntervalMs,
    });
    return response as CompletedModifyVideoResponse;
  }

  /**
   * Start a video modification task; returns immediately with a task id.
   * @param params Modification parameters.
   * @param options Per-request overrides.
   * @returns The task creation result with id.
   */
  async create(params: ModifyVideoParams, options?: RequestOptions): Promise<TaskCreateResponse> {
    const body = compactParams(params);
    validateParams(contract['modify-video'] as ActionSchema, body as Record<string, unknown>);
    return this.http.request<TaskCreateResponse>('POST', ENDPOINT, {
      body,
      ...options,
    });
  }

  /**
   * Fetch the current status of a video modification task.
   * @param id The task id.
   * @param options Per-request overrides.
   * @returns The current video modification task status.
   */
  async get(id: string, options?: RequestOptions): Promise<ModifyVideoResponse> {
    return this.http.request<ModifyVideoResponse>('GET', `${ENDPOINT}/${id}`, {
      ...options,
    });
  }
}
