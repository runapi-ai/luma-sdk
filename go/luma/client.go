package luma

import (
	"context"

	"github.com/runapi-ai/core-sdk/go/core"
	"github.com/runapi-ai/core-sdk/go/option"
)

const (
	modifyVideoPath = "/api/v1/luma/modify_video"
)

type Client struct {
	ModifyVideo *ModifyVideo
}

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

func NewClientWithHTTP(httpClient core.HTTPClient) *Client {
	return &Client{
		ModifyVideo: &ModifyVideo{http: httpClient},
	}
}

type ModifyVideo struct{ http core.HTTPClient }

func (r *ModifyVideo) Create(ctx context.Context, params ModifyVideoParams, opts ...option.RequestOption) (*core.TaskCreateResponse, error) {
	requestOptions, _ := option.ResolveRequestOptions(opts...)
	return core.PostJSON[core.TaskCreateResponse](ctx, r.http, modifyVideoPath, core.CompactParams(params), requestOptions)
}

func (r *ModifyVideo) Get(ctx context.Context, id string, opts ...option.RequestOption) (*ModifyVideoResponse, error) {
	requestOptions, _ := option.ResolveRequestOptions(opts...)
	return core.GetJSON[ModifyVideoResponse](ctx, r.http, core.ResourcePath(modifyVideoPath, id), requestOptions)
}

func (r *ModifyVideo) Run(ctx context.Context, params ModifyVideoParams, opts ...option.RequestOption) (*ModifyVideoResponse, error) {
	_, pollingOptions := option.ResolveRequestOptions(opts...)
	return core.RunAsync(ctx, func(ctx context.Context) (*core.TaskCreateResponse, error) {
		return r.Create(ctx, params, opts...)
	}, func(ctx context.Context, id string) (*ModifyVideoResponse, error) {
		return r.Get(ctx, id, opts...)
	}, pollingOptions)
}
