require 'test/unit'
require 'mapreduce'

class MapReduceTest < Test::Unit::TestCase

  def test_mapreduce
    m = MapReduce.new([['a','b'],['b','c']])
    r = m.mapreduce(2,2)
    assert_equal 1, r['a']
    assert_equal 2, r['b']
    assert_equal 1, r['c']
  end

end
