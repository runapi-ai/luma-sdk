# frozen_string_literal: true

require "runapi/core"
require_relative "luma/types"
require_relative "luma/contract_gen"
require_relative "luma/resources/modify_video"
require_relative "luma/client"

module RunApi
  module Luma
    AuthenticationError = RunApi::Core::AuthenticationError
    RateLimitError = RunApi::Core::RateLimitError
    InsufficientCreditsError = RunApi::Core::InsufficientCreditsError
    NotFoundError = RunApi::Core::NotFoundError
    ValidationError = RunApi::Core::ValidationError
    TaskFailedError = RunApi::Core::TaskFailedError
    TaskTimeoutError = RunApi::Core::TaskTimeoutError
  end
end
