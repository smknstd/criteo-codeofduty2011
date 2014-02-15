require 'rakejava'
require 'rake/clean'

CLEAN.include 'build'

task :default => :compile

javac :compile => :copytxt do |t|
    t.src << Sources["src", "**/*.java"]
    t.dest = 'build'
end

task :copytxt => "build" do
  copy_to "build" do |c|
    c.files << CopyFiles['data', '**/*.txt']
  end
end

directory "build"
