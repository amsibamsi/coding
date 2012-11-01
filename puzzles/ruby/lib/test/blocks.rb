require 'test/unit'
require 'blocks'

class TimeSortTest < Test::Unit::TestCase

  def setup
    @b0 = IsometricBlock.new(1,1,5,5,2,2)
    @b1 = IsometricBlock.new(7,1,5,5,2,2)
    @b2 = IsometricBlock.new(4,7,5,5,2,2)
    @m1 = Map.new([@b1, @b2, @b0])
  end

  def test_sort
    s = @m1.sort
    assert_equal s[0], @b0
    assert_equal s[1], @b1
    assert_equal s[2], @b2
  end

  def test_tsort
    s = @m1.tsort
    assert_equal s[0], @b0
    assert_equal s[1], @b1
    assert_equal s[2], @b2
  end

end
