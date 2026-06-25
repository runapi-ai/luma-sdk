export const contract = {
  "modify-video": {
    "models": [
      "luma-modify-video"
    ],
    "fields_by_model": {
      "luma-modify-video": {
        "prompt": {
          "required": true
        },
        "source_video_url": {
          "required": true
        }
      }
    }
  }
} as const;
