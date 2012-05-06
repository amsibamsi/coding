#!/usr/bin/env ruby


require 'set'

hits = 0
misses = 0
for count in 1..STDIN.gets.to_i
  min,max = STDIN.gets.split(' ').collect { |n| n.to_i }
  pairs = 0
  cache = Hash.new
  number = min
  while number <= max
    if number > 9
      if cache[number]
        hits += 1
        pairs += cache[number]
      else
        misses += 1
        length = Math.log10(number).ceil
        permutation = number
        permutations = [permutation]
        (length-1).times do
          permutation = permutation / 10 + (permutation % 10) * (10 ** (length - 1))
          permutations += [permutation] if permutation >= min and permutation <= max
        end
        permutations = permutations.uniq.sort
        index = 0
        while index <= (permutations.size - 1)
          cache[permutations[index]] = permutations.size - index - 1
          index += 1
        end
        pairs += cache[number]
      end
    end
    number += 1
  end
  puts "Case ##{count}: #{pairs}"
  warn "cache hits: #{hits}, misses: #{misses}"
end
