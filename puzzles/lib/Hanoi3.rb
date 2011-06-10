# = Towers of Hanoi
#
# Represents the Towers of Hanoi limited to exactly 3 towers.

class Hanoi3
  
  def initialize(height)
    @height = height
  end
  
  attr_reader :height 
  
  private
  
  # hanoi(int, int, int, int)
  def hanoi(height, from, to, by)
    if height == 1
      @moves += [[from, to]]
    elsif height > 1
      hanoi(height - 1, from, by, to)
      hanoi(1, from, to, by)
      hanoi(height - 1, by, to, from)
    end
  end
   
  public
  
  # Move the plates from tower 1 to tower 3.
  # 
  # Returns a SortedSet with moves [from, to].
  def solve
    @moves = []
    hanoi(@height, 1, 3, 2)
    return @moves
  end
  
end
