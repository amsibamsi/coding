require 'test/unit' 
require 'substring2'

class SubString2Test < Test::Unit::TestCase

  def test_shortest
    s = SubString2.new("abacus", "abcc")
    assert_equal "bac", s.shortest
  end

end
