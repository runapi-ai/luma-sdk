# frozen_string_literal: true

require "spec_helper"

RSpec.describe RunApi::Luma::Resources::ModifyVideo do
  let(:http) { instance_double(RunApi::Core::HttpClient) }
  let(:resource) { described_class.new(http) }
  let(:endpoint) { "/api/v1/luma/modify_video" }

  describe "#create" do
    it "POSTs to the correct endpoint with params" do
      params = {
        model: "luma-modify-video",
        prompt: "Turn the street into a rainy cyberpunk night with neon reflections",
        source_video_url: "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
        callback_url: "https://your-domain.com/api/callbacks/luma"
      }
      expect(http).to receive(:request).with(:post, endpoint, body: params)
        .and_return("id" => "task-1", "status" => "processing")

      result = resource.create(**params)
      expect(result).to be_a(RunApi::Luma::Types::ModifyVideoResponse)
      expect(result.id).to eq("task-1")
    end

    it "raises ValidationError when model is invalid" do
      expect { resource.create(prompt: "test", source_video_url: "https://x/v.mp4") }
        .to raise_error(RunApi::Core::ValidationError, /model must be one of: luma-modify-video/)
    end

    it "raises ValidationError when prompt is missing" do
      expect do
        resource.create(model: "luma-modify-video", source_video_url: "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4")
      end.to raise_error(RunApi::Core::ValidationError, /prompt is required/)
    end

    it "raises ValidationError when source_video_url is missing" do
      expect { resource.create(model: "luma-modify-video", prompt: "test") }
        .to raise_error(RunApi::Core::ValidationError, /source_video_url is required/)
    end
  end

  describe "#get" do
    it "GETs the correct endpoint" do
      expect(http).to receive(:request).with(:get, "#{endpoint}/task-1")
        .and_return(
          "id" => "task-1",
          "status" => "completed",
          "videos" => [{"url" => "https://tempfile.runapi.ai/generated-video.mp4"}],
          "sources" => [{"url" => "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4"}]
        )

      result = resource.get("task-1")
      expect(result).to be_a(RunApi::Luma::Types::ModifyVideoResponse)
      expect(result.status).to eq("completed")
      expect(result.videos.first.url).to eq("https://tempfile.runapi.ai/generated-video.mp4")
    end
  end

  describe "#run" do
    it "creates then polls until complete" do
      params = {
        model: "luma-modify-video",
        prompt: "Turn the street into a rainy cyberpunk night with neon reflections",
        source_video_url: "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4"
      }
      expect(http).to receive(:request).with(:post, endpoint, body: params)
        .and_return("id" => "task-1", "status" => "processing")
      expect(http).to receive(:request).with(:get, "#{endpoint}/task-1")
        .and_return("id" => "task-1", "status" => "processing")
      expect(http).to receive(:request).with(:get, "#{endpoint}/task-1")
        .and_return(
          "id" => "task-1",
          "status" => "completed",
          "videos" => [{"url" => "https://tempfile.runapi.ai/generated-video.mp4"}]
        )

      allow(RunApi::Core::Polling).to receive(:sleep)

      result = resource.run(**params)
      expect(result.videos.first.url).to eq("https://tempfile.runapi.ai/generated-video.mp4")
    end
  end
end
