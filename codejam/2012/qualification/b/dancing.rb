#!/usr/bin/env ruby


max = STDIN.gets.to_i
for count in 1..max
  numbers = STDIN.gets.split(' ').collect { |number| number.to_i }
  highs = 0
  surprises,best = numbers[1..2]
  numbers[3..-1].each {
    |points|
    mean = points / 3
    rest = points % 3
    case rest
    when 0
      if mean >= best
        highs += 1
      elsif (mean + 1) >= best and (mean + 1) <= 10 and (mean - 1) >= 0 and surprises > 0
        highs += 1
        surprises -= 1
      end
    when 1
      highs += 1 if (mean + 1) >= best
    when 2
      if (mean + 1) >= best
        highs += 1
      elsif (mean + 2) >= best and (mean + 2) <= 10 and surprises > 0
          highs += 1
          surprises -= 1
      end
    end
  }
  puts "Case ##{count}: #{highs}"
end
