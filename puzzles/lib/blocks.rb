require 'set'


class IsometricBlock

  attr_reader :x, :x2, :x3, :y, :y2, :y3

  def initialize(x, y, width, height, borderWidth, borderHeight)
    @x = x
    @x2 = @x + width
    @x3 = @x2 + borderWidth
    @y = y
    @y2 = @y + height
    @y3 = @y2 + borderHeight
  end

  def intertwine?(block)
    block.x2 > @x and
      block.y2 > @y and
      block.x < @x2 and
      block.y < @y2
  end

  def overlap?(block)
    (@y >= block.y2 and @y < block.y3 and @x2 > block.x and @x < block.x3) or
      (@x >= block.x2 and @x < block.x3 and @y3 > block.y and @y < block.y3)
  end

  def after?(block)
    @y >= block.y2 or
      (@y2 > block.y and @x >= block.x2)
  end

  def <=>(block)
    if self.after?(block)
      return 1
    elsif block.after?(self)
      return -1
    end
    return 0
  end

end

class Map

  def initialize(blocks)
    @blocks = blocks
  end

  def sort
    s = SortedSet.new
    @blocks.each { |b| s.add b }
    return s
  end

  def tsort
    ins = Hash.new
    outs = Hash.new
    @blocks.each do
      |b|
      ins[b] = Set.new
      outs[b] = Set.new
    end
    @blocks.each do
      |b1|
      @blocks.each do
        |b2|
        if b2.overlap?(b1)
          outs[b1].add(b2)
          ins[b2].add(b1)
        end
      end
    end
    s = []
    todo = Set.new
    current = Set.new
    @ins.each do
      |k,v|
      current << k if v.empty?
    end
    while not current.empty? do
      current.each do
        |c|
        outs[c].each do
          |o|
          ins[o].delete(c)
          todo.add(o) if ins[o].empty?
        end
        outs[c] = Set.new
      end
      current = Set.new
      current.add(todo)
      todo = Set.new
    end
  end

end
