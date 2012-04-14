#!/usr/bin/env ruby


require 'set'

hit = 0
miss = 0
for count in 1..STDIN.gets.to_i
  min,max = STDIN.gets.split(' ').collect { |n| n.to_i }
  pairs = 0
  cache = Hash.new
  for number in min..max
    if number > 9
      if cache[number]
        hit += 1
        pairs += cache[number]
      else
        miss += 1
        chars = String(number).chars.to_a
        permutations = SortedSet.new
        permutations.add number
        for index in 1..(chars.size-1)
          recycled = (chars[index..-1] + chars[0..(index-1)]).to_s.to_i
          if recycled >= min and recycled <= max
            permutations.add recycled
          end
        end
        for index in 0..(permutations.size-1)
          cache[permutations.to_a[index]] = permutations.size - index - 1
        end
        pairs += cache[number]
      end
    end
  end
  puts "Case ##{count}: #{pairs}"
  warn "cache hit: #{hit}, miss: #{miss}"
end
