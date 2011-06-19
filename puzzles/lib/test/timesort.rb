require 'test/unit'
require 'timesort'

class TimeSortTest < Test::Unit::TestCase

  def test_timesort
    t = TimeSort.new
    assert_equal [0.3,0.7,1], t.sort([0.7,0.3,1])
  end

end
