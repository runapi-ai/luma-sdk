import type { HttpClient, PollingOptions, RequestOptions } from '@runapi.ai/core';
import { compactParams } from '@runapi.ai/core';
import { pollUntilComplete } from '@runapi.ai/core/internal';
import type {
  CompletedModifyVideoResponse,
  TaskCreateResponse,
  ModifyVideoParams,
  ModifyVideoResponse,
} from '../types';

const ENDPOINT = '/api/v1/luma/modify_video';

export class ModifyVideo {
  constructor(private readonly http: HttpClient) {}

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

  async create(params: ModifyVideoParams, options?: RequestOptions): Promise<TaskCreateResponse> {
    return this.http.request<TaskCreateResponse>('POST', ENDPOINT, {
      body: compactParams(params),
      ...options,
    });
  }

  async get(id: string, options?: RequestOptions): Promise<ModifyVideoResponse> {
    return this.http.request<ModifyVideoResponse>('GET', `${ENDPOINT}/${id}`, {
      ...options,
    });
  }
}
