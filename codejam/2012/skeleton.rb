#!/usr/bin/env ruby


input = ARGV.empty? ? STDIN : File.new(ARGV[0], 'r')

tests = input.gets.to_i
for t in 1..tests
  print "Case ##{t}:"
  numbers = input.gets.split(' ').collect { |n| n.to_i }

  # do stuff, print results

  puts
end
