package luma

import (
	"context"
	"encoding/json"
	"testing"

	"github.com/runapi-ai/core-sdk/go/core"
)

type stubHTTPClient struct {
	method   string
	path     string
	body     any
	response json.RawMessage
}

func (s *stubHTTPClient) Request(_ context.Context, method, path string, opts *core.HTTPRequestOptions) (json.RawMessage, error) {
	s.method = method
	s.path = path
	if opts != nil {
		s.body = opts.Body
	}
	return s.response, nil
}

func TestModifyVideoCreate(t *testing.T) {
	stub := &stubHTTPClient{response: json.RawMessage(`{"id":"task_luma_123","status":"processing"}`)}
	client := NewClientWithHTTP(stub)
	resp, err := client.ModifyVideo.Create(context.Background(), ModifyVideoParams{
		Prompt:         "Turn the street into a rainy cyberpunk night with neon reflections",
		SourceVideoURL: "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
		CallbackURL:    "https://your-domain.com/api/callbacks/luma",
	})
	if err != nil {
		t.Fatal(err)
	}
	if stub.method != "POST" || stub.path != modifyVideoPath {
		t.Fatalf("unexpected request: %s %s", stub.method, stub.path)
	}
	body := stub.body.(map[string]any)
	if body["source_video_url"] != "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4" {
		t.Fatalf("unexpected source_video_url: %v", body["source_video_url"])
	}
	if _, ok := body["video_url"]; ok {
		t.Fatalf("expected request body to omit provider video_url key: %v", body)
	}
	if resp.ID != "task_luma_123" {
		t.Fatalf("unexpected ID: %v", resp.ID)
	}
}

func TestModifyVideoGet(t *testing.T) {
	stub := &stubHTTPClient{response: json.RawMessage(`{"id":"task_luma_456","status":"completed","videos":[{"url":"https://tempfile.runapi.ai/generated-video.mp4"}],"sources":[{"url":"https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4"}]}`)}
	client := NewClientWithHTTP(stub)
	resp, err := client.ModifyVideo.Get(context.Background(), "task_luma_456")
	if err != nil {
		t.Fatal(err)
	}
	if stub.method != "GET" || stub.path != modifyVideoPath+"/task_luma_456" {
		t.Fatalf("unexpected request: %s %s", stub.method, stub.path)
	}
	if resp.Status != "completed" {
		t.Fatalf("unexpected status: %v", resp.Status)
	}
	if len(resp.Videos) != 1 || resp.Videos[0].URL != "https://tempfile.runapi.ai/generated-video.mp4" {
		t.Fatalf("unexpected videos: %v", resp.Videos)
	}
	if len(resp.Sources) != 1 || resp.Sources[0].URL != "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4" {
		t.Fatalf("unexpected sources: %v", resp.Sources)
	}
}
