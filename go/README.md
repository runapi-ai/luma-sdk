# Luma API Go SDK for RunAPI

Luma Modify Video (`luma-modify-video`) was retired on 2026-10-14. New requests return HTTP 410 before task creation or balance deduction. The old ID does not map to a replacement. This package remains available for historical task lookup.

This README is the Go package guide inside the public `luma-sdk` repository. For the repository overview, start at `../README.md`; for model details, use https://runapi.ai/models/luma; for API reference, use https://runapi.ai/docs/api/luma/modify-video; for SDK docs, use https://runapi.ai/docs/resources/sdks.

## Install

```bash
go get github.com/runapi-ai/luma-sdk/go@latest
```

## Historical task lookup

```go
import (
  "context"
  "log"

  "github.com/runapi-ai/luma-sdk/go/luma"
)

client, err := luma.NewClient()
if err != nil {
  log.Fatal(err)
}
status, err := client.ModifyVideo.Get(context.Background(), "existing-task-id")
if err != nil {
  log.Fatal(err)
}
log.Print(status)
```

Use `get` for an existing task ID. Luma has no callable model for new tasks. Do not use `create` or `run` to submit Luma work.

RunAPI-generated file URLs are temporary. Store files from historical results in your own durable storage.

## Language notes

Keep `RUNAPI_API_KEY` in the environment or your secret manager. Use the task-status response to inspect historical results and errors.

## Links

- Model page: https://runapi.ai/models/luma
- SDK docs: https://runapi.ai/docs/resources/sdks
- Product docs: https://runapi.ai/docs/api/luma/modify-video
- Retirement reference: https://runapi.ai/models/luma
- Provider comparison: https://runapi.ai/providers/luma
- Full catalog: https://runapi.ai/models
- Repository: https://github.com/runapi-ai/luma-sdk

## License

Licensed under the Apache License, Version 2.0.
