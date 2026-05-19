import { beforeEach, describe, expect, it, vi } from 'vitest';
import type { HttpClient } from '@runapi.ai/core';
import { ModifyVideo } from '../../src/resources/modify-video';

describe('Luma modify videos', () => {
  const mockHttp: HttpClient = {
    request: vi.fn(),
  };

  beforeEach(() => {
    vi.clearAllMocks();
  });

  it('creates a modify video with flat params', async () => {
    vi.mocked(mockHttp.request).mockResolvedValueOnce({ id: 'task-1', status: 'processing' });
    const resource = new ModifyVideo(mockHttp);

    await resource.create({
      prompt: 'Turn the street into a rainy cyberpunk night with neon reflections',
      video_url: 'https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4',
      callback_url: 'https://your-domain.com/api/callbacks/luma',
      watermark: 'demo-watermark',
    });

    expect(mockHttp.request).toHaveBeenCalledWith('POST', '/api/v1/luma/modify_video', {
      body: {
        prompt: 'Turn the street into a rainy cyberpunk night with neon reflections',
        video_url: 'https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4',
        callback_url: 'https://your-domain.com/api/callbacks/luma',
        watermark: 'demo-watermark',
      },
    });
  });

  it('gets a modify video by id', async () => {
    vi.mocked(mockHttp.request).mockResolvedValueOnce({
      id: 'task-1',
      status: 'completed',
      videos: [{ url: 'https://tempfile.runapi.ai/generated-video.mp4' }],
      sources: [{ url: 'https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4' }],
    });
    const resource = new ModifyVideo(mockHttp);

    const result = await resource.get('task-1');

    expect(mockHttp.request).toHaveBeenCalledWith('GET', '/api/v1/luma/modify_video/task-1', {});
    expect(result.videos?.[0]?.url).toBe('https://tempfile.runapi.ai/generated-video.mp4');
    expect(result.sources?.[0]?.url).toBe('https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4');
  });
});
