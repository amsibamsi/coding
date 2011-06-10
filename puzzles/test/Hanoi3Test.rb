require 'test/unit' 
require 'Hanoi3'

class Hanoi3Test < Test::Unit::TestCase

  def test_solve0
    m = Hanoi3.new(0).solve.to_a
    assert_equal m, []
  end

  def test_solve1
    m = Hanoi3.new(1).solve.to_a
    assert_equal m, [[1, 3]]
  end

  def test_solve2
    m = Hanoi3.new(2).solve.to_a
    assert_equal m, [[1, 2], [1, 3], [2, 3]]
  end
  
  def test_solve3
    m = Hanoi3.new(3).solve.to_a
    assert_equal m, [[1, 3], [1, 2], [3, 2], [1, 3], [2, 1], [2, 3], [1, 3]]
  end

end
