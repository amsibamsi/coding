require 'set'


class IsometricBlock

  attr_reader :x, :y, :width, :height, :border

  def initialize(x, x2, width, height, borderWidth, borderHeight)
    @x = x
    @x2 = x + width
    @x3 = @x2 + borderWidth
    @y = y
    @y2 = y + height
    @y3 = @y2 + borderHeight
  end

  def intertwine?(block)
    block.x2 > x
      and block.y2 > y
      and block.x < x2
      and block.y < y2
  end

  def overlap?(block)
    (y >= block.y2 and y < block.y3 and x2 > block.x and x < block.x3)
      or (x >= block.x2 and x < block.x3 and y3 > block.y and y < block.y3)
  end

  # like overlap, but also when blocks do not overlap
  def after?(block)
    y >= block.y2
      or (y2 > block.y and x >= block.x2)
  end

  def <=>(block)
    if self.overlap?(block)
      return 1
    elsif block.overlap?(self)
      return -1
    end
    return 0
  end

  # implement hash/equal?

end

class Map

  def initialize()
    @blocks = []
  end

  def sort
    s = SortedSet.new
    @blocks.each { |b| s.add b }
    return s
  end

  def tsort

  end

end
