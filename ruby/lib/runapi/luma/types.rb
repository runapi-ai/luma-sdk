# frozen_string_literal: true

module RunApi
  module Luma
    # Type definitions and constants for Luma video modification.
    module Types
      # A video file with a download URL.
      class Video < RunApi::Core::BaseModel
        optional :url, String
      end

      # Base async task response with id and status tracking.
      class AsyncTaskResponse < RunApi::Core::TaskResponse
        required :id, String
        optional :status, String, enum: -> { RunApi::Core::TaskResponse::Status::ALL }
      end

      # Task status response for a video modification operation.
      # On completion, +videos+ contains the modified output and +sources+ contains the original input.
      class ModifyVideoResponse < AsyncTaskResponse
        optional :videos, [-> { Video }]
        optional :sources, [-> { Video }]
        optional :error, String
      end

      # Completed video modification response with guaranteed output videos.
      class CompletedModifyVideoResponse < ModifyVideoResponse
        required :videos, [-> { Video }]
      end
    end
  end
end
