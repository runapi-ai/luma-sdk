package luma

type TaskStatus string

type ModifyVideoParams struct {
	Prompt         string `json:"prompt" help:"required; English prompt describing the video changes"`
	SourceVideoURL string `json:"source_video_url" help:"required; publicly accessible source video URL"`
	CallbackURL    string `json:"callback_url,omitempty" help:"optional; HTTPS callback URL for completion events"`
	Watermark      string `json:"watermark,omitempty" help:"optional; watermark identifier"`
}

type AsyncTaskResponse struct {
	ID     string     `json:"id"`
	Status TaskStatus `json:"status"`
	Error  string     `json:"error,omitempty"`
}

func (r AsyncTaskResponse) GetID() string     { return r.ID }
func (r AsyncTaskResponse) GetStatus() string { return string(r.Status) }
func (r AsyncTaskResponse) GetError() string  { return r.Error }

type Video struct {
	URL string `json:"url"`
}

type ModifyVideoResponse struct {
	AsyncTaskResponse
	Videos  []Video `json:"videos,omitempty"`
	Sources []Video `json:"sources,omitempty"`
}
