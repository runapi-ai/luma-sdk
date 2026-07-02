// Package luma provides the Luma video modification API client.
//
// Luma applies prompt-guided edits to an existing video, such as changing visual style,
// adjusting atmosphere, or adding effects while preserving the source motion.
//
//	client, err := luma.NewClient(option.WithAPIKey("sk-your-api-key"))
//	result, err := client.ModifyVideo.Run(ctx, luma.ModifyVideoParams{
//	    Model:          "luma-modify-video",
//	    Prompt:         "Add falling snow and a blue color grade",
//	    SourceVideoURL: "https://cdn.runapi.ai/public/samples/video.mp4",
//	})
package luma

import (
	"context"

	"github.com/runapi-ai/core-sdk/go/base"
	"github.com/runapi-ai/core-sdk/go/core"
	"github.com/runapi-ai/core-sdk/go/option"
)

const (
	modifyVideoPath = "/api/v1/luma/modify_video"
)

// Client provides Luma prompt-driven video modification.
type Client struct {
	base.Base
	ModifyVideo *ModifyVideo
}

// NewClient creates a Luma client with the given options.
func NewClient(opts ...option.ClientOption) (*Client, error) {
	resolved, err := option.ResolveClientOptions(opts...)
	if err != nil {
		return nil, err
	}
	httpClient, err := core.NewHTTPClient(resolved)
	if err != nil {
		return nil, err
	}
	return NewClientWithHTTP(httpClient), nil
}

// NewClientWithHTTP creates a Luma client with a pre-configured HTTP transport.
func NewClientWithHTTP(httpClient core.HTTPClient) *Client {
	return &Client{
		Base:        base.New(httpClient),
		ModifyVideo: &ModifyVideo{http: httpClient},
	}
}

// ModifyVideo applies prompt-guided edits to an existing video.
// The source video's motion is preserved while visual changes described in the prompt are applied.
type ModifyVideo struct{ http core.HTTPClient }

// Create submits a modify-video task and returns immediately with a task id.
func (r *ModifyVideo) Create(ctx context.Context, params ModifyVideoParams, opts ...option.RequestOption) (*core.TaskCreateResponse, error) {
	requestOptions, _ := option.ResolveRequestOptions(opts...)
	body := core.CompactParams(params)
	if err := core.ValidateParams(contractSchema["modify-video"], body); err != nil {
		return nil, err
	}
	return core.PostJSON[core.TaskCreateResponse](ctx, r.http, modifyVideoPath, body, requestOptions)
}

// Get fetches the current status of a modify-video task by id.
func (r *ModifyVideo) Get(ctx context.Context, id string, opts ...option.RequestOption) (*ModifyVideoResponse, error) {
	requestOptions, _ := option.ResolveRequestOptions(opts...)
	return core.GetJSON[ModifyVideoResponse](ctx, r.http, core.ResourcePath(modifyVideoPath, id), requestOptions)
}

// Run submits a modify-video task and polls until it completes.
func (r *ModifyVideo) Run(ctx context.Context, params ModifyVideoParams, opts ...option.RequestOption) (*ModifyVideoResponse, error) {
	_, pollingOptions := option.ResolveRequestOptions(opts...)
	return core.RunAsync(ctx, func(ctx context.Context) (*core.TaskCreateResponse, error) {
		return r.Create(ctx, params, opts...)
	}, func(ctx context.Context, id string) (*ModifyVideoResponse, error) {
		return r.Get(ctx, id, opts...)
	}, pollingOptions)
}
