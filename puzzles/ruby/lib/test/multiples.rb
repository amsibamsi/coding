require 'test/unit' 
require 'multiples'

class MultiplesTest < Test::Unit::TestCase

  def test_multiples35
    assert_equal multiples35(10).sum, 23
    assert_equal multiples35(1000).sum, 233168
  end

  def test_multiples35_recursive
    assert_equal 23, multiples35_recursive(3, 5, 10).sum
    assert_equal 233168, multiples35_recursive(3, 5, 1000).sum
  end

end
