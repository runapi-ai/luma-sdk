# Luma Python SDK for RunAPI

The Luma Python SDK is the language-specific package for Luma on RunAPI. Use this luma package for video modification flows when your application needs JSON request bodies, task status lookup, and consistent RunAPI errors in Python.

This luma README is the Python package guide inside the public `luma-sdk` repository. For the repository overview, start at `../README.md`; for model details, use https://runapi.ai/models/luma; for API reference, use https://runapi.ai/docs#luma; for SDK docs, use https://runapi.ai/docs#sdk-luma.

## Install

```bash
pip install runapi-luma
```

## Quick start

```python
from runapi.luma import LumaClient

client = LumaClient()  # reads RUNAPI_API_KEY, or pass api_key="sk-..."

task = client.modify_video.create(
    model="ray-2-modify-video",
    prompt="Make it cinematic with warm golden tones",
    source_video_url="https://example.com/source.mp4",
)
status = client.modify_video.get(task.id)
```

Use `create` when you want to submit a task and return quickly, `get` when you need the latest task state, and `run` when a script should create and poll until completion:

```python
result = client.modify_video.run(
    model="ray-2-modify-video",
    prompt="Restyle as a hand-painted animation",
    source_video_url="https://example.com/source.mp4",
)
print(result.videos[0].url)
```

In web request handlers, prefer `create` plus webhook or later `get` polling so a worker is not held open.

RunAPI-generated file URLs are temporary. Download and store generated images, videos, audio, or other files in your own durable storage within 7 days; do not treat returned URLs as long-term assets.

## Language notes

Pass parameters as keyword arguments and catch the `runapi.luma` error classes when building video jobs, workers, or scripts. The available resource is `modify_video`. Keep `RUNAPI_API_KEY` in the environment or your secret manager; never commit API keys or callback secrets.

## Links

- Model page: https://runapi.ai/models/luma
- SDK docs: https://runapi.ai/docs#sdk-luma
- Product docs: https://runapi.ai/docs#luma
- Pricing and rate limits: https://runapi.ai/models/luma
- Provider comparison: https://runapi.ai/providers/luma
- Full catalog: https://runapi.ai/models
- Repository: https://github.com/runapi-ai/luma-sdk

## License

Licensed under the Apache License, Version 2.0.
