# frozen_string_literal: true

require "runapi/luma"

client = RunApi::Luma::Client.new(api_key: ENV.fetch("RUNAPI_API_KEY"))

task = client.modify_video.create(
  prompt: "Turn the street into a rainy cyberpunk night with neon reflections",
  source_video_url: "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4"
)

puts "task_id=#{task.id}"
