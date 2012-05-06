#!/usr/bin/env ruby


require 'set'


class DiamondInheritance

  def initialize(nodes, edges)
    @nodes = nodes
    @edges = edges
    @diamond = false
  end

  def diamonds?
    for node in 0..@nodes do
      if @edges[node].count > 1
        @visited = Array.new(@nodes, false)
        self.visit node
      end
    end
    return @diamond
  end

  def visit(n)
    if @visited[n]
      @diamond = true
      return
    else
      @visited[n] = true
      @edges[n].each do
        |e|
        self.visit(e)
      end
    end
  end

end

input = ARGV.empty? ? STDIN : File.new(ARGV[0], 'r')

tests = input.gets.to_i
for t in 1..tests
  print "Case ##{t}: "
  nodes = input.gets.to_i - 1
  edges = Array.new(nodes)
  for node in 0..nodes do
    numbers = input.gets.split(' ').collect { |i| i.to_i - 1 }
    edges[node] = numbers[1..-1].to_set
  end
  d = DiamondInheritance.new(nodes, edges)
  puts d.diamonds? ? 'Yes' : 'No'
end
