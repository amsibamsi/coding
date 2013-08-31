require 'test/unit' 
require 'rcube'

class DirectionRotationTest < Test::Unit::TestCase

  def test_rotate
    assert_equal Direction[:front],
      (Rotation[:up] * Direction[:right])
  end

end

class SideTest < Test::Unit::TestCase

  def setup
    @side = Side.new(Direction[:front])
  end

  def test_rotate
    @side.rotate(Rotation[:right])
    assert_equal Direction[:up], @side.direction
    @side.rotate(Rotation[:left])
    assert_equal Direction[:front], @side.direction
    @side.rotate(Rotation[:up])
    assert_equal Direction[:left], @side.direction
    @side.rotate(Rotation[:back])
    assert_equal Direction[:down], @side.direction
    @side.rotate(Rotation[:down])
    assert_equal Direction[:down], @side.direction
    @side.rotate(Rotation[:front])
    assert_equal Direction[:left], @side.direction
  end

  def test_color
    @side.color = Color.new(:red)
    assert_equal Color.new(:red), @side.color
  end

end

class CubeTest < Test::Unit::TestCase

  def setup
    @cube = Cube.new(Vector[0, 0, 0])
  end

  def test_colorSide
    @cube.set_color(Direction[:front], Color.new(:green))
    assert_equal Color.new(:green), @cube.get_color(Direction[:front])
  end

  def test_rotate
    side = @cube.side(Direction[:front])
    @cube.rotate(Rotation[:up])
    assert_equal side, @cube.side(Direction[:left])
  end

end
