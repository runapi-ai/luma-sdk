import { createHttpClient, type ClientOptions } from '@runapi.ai/core';
import { ModifyVideo } from './resources/modify-video';

export class LumaClient {
  public readonly modifyVideo: ModifyVideo;

  constructor(options: ClientOptions = {}) {
    const http = createHttpClient(options);
    this.modifyVideo = new ModifyVideo(http);
  }
}
