# frozen_string_literal: true

module RunApi
  module Luma
    CONTRACT = {
      "modify-video" => {
        "models" => ["luma-modify-video"],
        "fields_by_model" => {
          "luma-modify-video" => {
            "prompt" => {
              "required" => true
            },
            "source_video_url" => {
              "required" => true
            }
          }
        }
      }
    }.freeze
  end
end
