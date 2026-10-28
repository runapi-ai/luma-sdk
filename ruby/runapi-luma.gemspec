# frozen_string_literal: true

Dir.chdir(__dir__) do

  Gem::Specification.new do |spec|
    spec.name = "runapi-luma"
    spec.version = "0.3.0"
    spec.metadata["runapi_slug"] = "luma"
    spec.authors = ["RunAPI"]
    spec.email = ["contact@runapi.ai"]

    spec.summary = "Luma API Ruby SDK for RunAPI"
    spec.description = "Luma Modify Video (`luma-modify-video`) was retired on 2026-10-14. New requests return HTTP 410 before task creation or balance deduction. The old ID does not map to a replacement. This package remains available for historical task lookup."
    spec.homepage = "https://runapi.ai/models/luma"
    spec.license = "Apache-2.0"
    spec.required_ruby_version = ">= 3.1.0"
    spec.metadata["homepage_uri"] = "https://runapi.ai/models/luma"
    spec.metadata["documentation_uri"] = "https://github.com/runapi-ai/luma-sdk/blob/main/ruby/README.md"
    spec.metadata["source_code_uri"] = "https://github.com/runapi-ai/luma-sdk"
    spec.metadata["bug_tracker_uri"] = "https://github.com/runapi-ai/luma-sdk/issues"
    spec.metadata["changelog_uri"] = "https://github.com/runapi-ai/luma-sdk/blob/main/CHANGELOG.md"


    spec.files = Dir.glob("lib/**/*") + %w[LICENSE README.md]
    spec.extra_rdoc_files = ["README.md"]
        spec.require_paths = ["lib"]

    spec.add_dependency "runapi-core", "~> 0.6.1"
  end
end
