# frozen_string_literal: true

module RunApi
  module Luma
    module Types
      class Video < RunApi::Core::BaseModel
        optional :url, String
      end

      class AsyncTaskResponse < RunApi::Core::TaskResponse
        required :id, String
        optional :status, String, enum: -> { RunApi::Core::TaskResponse::Status::ALL }
      end

      class ModifyVideoResponse < AsyncTaskResponse
        optional :videos, [-> { Video }]
        optional :sources, [-> { Video }]
        optional :error, String
      end

      class CompletedModifyVideoResponse < ModifyVideoResponse
        required :videos, [-> { Video }]
      end
    end
  end
end
