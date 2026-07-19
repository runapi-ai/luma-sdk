# frozen_string_literal: true

require "spec_helper"

RSpec.describe RunApi::Luma::Client do
  before do
    allow(ConnectionPool).to receive(:new).and_return(instance_double(ConnectionPool))
  end

  after { RunApi.api_key = nil }

  it "accepts api_key as parameter" do
    client = described_class.new(api_key: "param-key")
    expect(client).to be_a(described_class)
  end

  it "falls back to global RunApi.api_key" do
    RunApi.api_key = "global-key"
    client = described_class.new
    expect(client).to be_a(described_class)
  end

  it "raises AuthenticationError without api_key" do
    expect { described_class.new }.to raise_error(RunApi::Core::AuthenticationError, /API key is required/)
  end

  it "exposes the modify_video resource" do
    client = described_class.new(api_key: "test-key")
    expect(client.modify_video).to be_a(RunApi::Luma::Resources::ModifyVideo)
  end
end
