require 'test/unit' 
require 'hanoi'

class HanoiTest < Test::Unit::TestCase

  def test_solve0
    m = Hanoi.new(0).solve.to_a
    assert_equal m, []
  end

  def test_solve1
    m = Hanoi.new(1).solve.to_a
    assert_equal m, [[1, 3]]
  end

  def test_solve2
    m = Hanoi.new(2).solve.to_a
    assert_equal m, [[1, 2], [1, 3], [2, 3]]
  end
  
  def test_solve3
    m = Hanoi.new(3).solve.to_a
    assert_equal m, [[1, 3], [1, 2], [3, 2], [1, 3], [2, 1], [2, 3], [1, 3]]
  end

end
