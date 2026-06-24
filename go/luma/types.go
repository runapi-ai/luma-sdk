// Package luma provides the Luma video modification API client.
package luma

// TaskStatus is the async task lifecycle state (e.g. "processing", "completed", "failed").
type TaskStatus string

// ModifyVideoParams configures prompt-guided video modification.
// Prompt must be in English. SourceVideoURL must be publicly accessible.
type ModifyVideoParams struct {
	Model          string `json:"model" help:"required; model slug"`
	Prompt         string `json:"prompt" help:"required; English prompt describing the video changes"`
	SourceVideoURL string `json:"source_video_url" help:"required; publicly accessible source video URL"`
	CallbackURL    string `json:"callback_url,omitempty" help:"optional; HTTPS callback URL for completion events"`
	Watermark      string `json:"watermark,omitempty" help:"optional; watermark identifier"`
}

// AsyncTaskResponse carries the task ID, lifecycle status, and error for Luma async operations.
type AsyncTaskResponse struct {
	ID     string     `json:"id"`
	Status TaskStatus `json:"status"`
	Error  string     `json:"error,omitempty"`
}

func (r AsyncTaskResponse) GetID() string     { return r.ID }
func (r AsyncTaskResponse) GetStatus() string { return string(r.Status) }
func (r AsyncTaskResponse) GetError() string  { return r.Error }

// Video holds a URL to a video file.
type Video struct {
	URL string `json:"url"`
}

// ModifyVideoResponse is the result of a completed ModifyVideo task.
// Videos contains the modified output. Sources contains the original input video(s).
type ModifyVideoResponse struct {
	AsyncTaskResponse
	Videos  []Video `json:"videos,omitempty"`
	Sources []Video `json:"sources,omitempty"`
}
