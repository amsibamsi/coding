require 'test/unit' 
require 'substring'

class SubStringTest < Test::Unit::TestCase

  def test_shortest
    s = SubString.new("abacus", "abcc")
    assert_equal "bac", s.shortest
  end

end
