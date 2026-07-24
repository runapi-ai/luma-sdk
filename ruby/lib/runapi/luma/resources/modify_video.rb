# frozen_string_literal: true

module RunApi
  module Luma
    module Resources
      # Luma video modification resource.
      # Apply prompt-guided visual edits to an existing video while preserving its motion.
      class ModifyVideo
        include RunApi::Core::ResourceHelpers

        ENDPOINT = "/api/v1/luma/modify_video"
        RESPONSE_CLASS = Types::ModifyVideoResponse
        COMPLETED_RESPONSE_CLASS = Types::CompletedModifyVideoResponse

        def initialize(http)
          @http = http
        end

        # Modify a video and wait until complete.
        #
        # @param params [Hash] modification parameters
        # @return [RunApi::Luma::Types::CompletedModifyVideoResponse] completed task with videos
        def run(options: nil, **params)
          task = create(options: options, **params)
          poll_until_complete { get(task.id, options: options) }
        end

        # Start a video modification task.
        #
        # @param params [Hash] modification parameters
        # @return [RunApi::Luma::Types::ModifyVideoResponse] task creation result with id
        def create(options: nil, **params)
          params = compact_params(params)
          validate_contract!(CONTRACT["modify-video"], params)
          request(:post, ENDPOINT, body: params, options: options)
        end

        # Get video modification task status by task ID.
        #
        # @param id [String] task ID
        # @return [RunApi::Luma::Types::ModifyVideoResponse] current task status
        def get(id, options: nil)
          request(:get, "#{ENDPOINT}/#{id}", options: options)
        end
      end
    end
  end
end
