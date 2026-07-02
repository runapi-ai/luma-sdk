import { BaseClient, type ClientOptions } from '@runapi.ai/core';
import { ModifyVideo } from './resources/modify-video';

/**
 * Luma prompt-guided video modification API client.
 * Applies visual edits to an existing video -- changing style, adjusting atmosphere,
 * or adding effects -- while preserving the source motion.
 *
 * @example
 * ```typescript
 * const client = new LumaClient({ apiKey: 'your-api-key' });
 *
 * const result = await client.modifyVideo.run({
 *   prompt: 'Add a dramatic sunset lighting effect',
 *   source_video_url: 'https://cdn.runapi.ai/public/samples/video.mp4',
 * });
 * ```
 */
export class LumaClient extends BaseClient {
  /** Apply prompt-guided visual edits to an existing video while preserving its motion. */
  public readonly modifyVideo: ModifyVideo;

  constructor(options: ClientOptions = {}) {
    super(options);
    this.modifyVideo = new ModifyVideo(this.http);
  }
}
