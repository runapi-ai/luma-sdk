# frozen_string_literal: true

module RunApi
  module Luma
    class Client
      attr_reader :modify_video

      def initialize(api_key: nil, **options)
        @api_key = Core::Auth.resolve_api_key(api_key)

        client_options = Core::ClientOptions.new(api_key: @api_key, **options)
        http = client_options.http_client || Core::HttpClient.new(client_options)
        @modify_video = Resources::ModifyVideo.new(http)
      end
    end
  end
end
