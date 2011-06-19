require 'test/unit'
require 'hashsort'

class HashTest < Test::Unit::TestCase

  def test_hashsort
    h = HashSort.new
    assert_equal [5,6,170], h.sort([170,6,5])
  end

end
