#!/usr/bin/env ruby


max = STDIN.gets.to_i
for count in 1..max
  numbers = STDIN.gets.split(' ').collect { |n| n.to_i }
  amount = numbers[0]
  points = numbers[1..-1]
  votes = 0
  points.each { |p| votes += p }
  total = (votes * 2).to_f
  average = total / amount
  criticals = points.clone
  max = criticals.max
  while max > average
    amount -= 1
    total -= max
    criticals.delete_at(criticals.index(max))
    average = total / amount
    max = criticals.max
  end
  mins = []
  points.each {
    |p|
    if p >= average
      mins << 0.0
    else
      mins << (average - p) / votes * 100
    end
  }
  print "Case ##{count}:"
  mins.each { |m| print " #{m}" }
  puts
end
