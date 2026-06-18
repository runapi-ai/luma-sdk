# frozen_string_literal: true

module RunApi
  module Luma
    # Luma prompt-guided video modification API client.
    # Applies visual edits to an existing video -- changing style, adjusting atmosphere,
    # or adding effects -- while preserving the source motion.
    #
    # @example
    #   client = RunApi::Luma::Client.new(api_key: "your-api-key")
    #   result = client.modify_video.run(
    #     prompt: "Add a dramatic sunset lighting effect",
    #     source_video_url: "https://example.com/input.mp4"
    #   )
    class Client < RunApi::Core::Client
      # @return [Resources::ModifyVideo] Prompt-guided video editing that preserves source motion.
      attr_reader :modify_video

      def initialize(api_key: nil, **options)
        super
        @modify_video = Resources::ModifyVideo.new(http)
      end
    end
  end
end
