require 'matrix'
require 'set'

class Direction

  def self.[](symbol)
    case symbol
    when :right
      Vector[1, 0, 0]
    when :left
      Vector[-1, 0, 0]
    when :up
      Vector[0, 1, 0]
    when :down
      Vector[0, -1, 0]
    when :front
      Vector[0, 0, 1]
    when :back
      Vector[0, 0, -1]
    else
      raise "invalid direction"
    end
  end

end

# rotations are understood as turning 90 degrees counterclockwise on one of the 6 unit vectors on the 3 axes
class Rotation

  def self.[](symbol)
    case symbol
    when :right
      Matrix[[1,0,0],[0,0,1],[0,-1,0]]
    when :left
      Matrix[[1,0,0],[0,0,-1],[0,1,0]]
    when :up
      Matrix[[0,0,-1],[0,1,0],[1,0,0]]
    when :down
      Matrix[[0,0,1],[0,1,0],[-1,0,0]]
    when :front
      Matrix[[0,1,0],[-1,0,0],[0,0,1]]
    when :back
      Matrix[[0,-1,0],[1,0,0],[0,0,1]]
    else
      raise "invalid rotation"
    end
  end

end

class Color

  attr_reader :text

  def initialize(symbol = :none)
    case symbol
    when :white
      @text ="\033[47m  \033[0m"
    when :red
      @text = "\033[41m  \033[0m"
    when :green
      @text = "\033[42m  \033[0m"
    when :blue
      @text = "\033[44m  \033[0m"
    when :yellow
      @text = "\033[43m  \033[0m"
    when :orange
      @text = "\033[45m  \033[0m"
    when :none
      @text = "  "
    else
      raise "invalid color"
    end
  end

  def draw
    print @text
  end

  def hash
    return @text.hash
  end

  def ==(other)
    return @text == other.text
  end

end

class Side

  attr_reader :direction
  attr_accessor :color

  def initialize(direction)
    @color = Color.new
    @direction = direction
  end

  def rotate(rotation)
    @direction = rotation * @direction
  end

  def draw
    @color.draw
  end

end

class Cube

  attr_reader :position

  def initialize(position)
    @position = position
    @sides = Set.new
    [:right, :left, :up, :down, :front, :back].each {
      |direction|
      @sides << Side.new(Direction[direction])
    }
  end

  def side(direction)
    return @sides.detect { |side| side.direction == direction }
  end

  def set_color(direction, color)
    self.side(direction).color = color
  end

  def get_color(direction)
    return self.side(direction).color
  end

  def rotate(rotation)
    @sides.each { |side| side.rotate(rotation) }
    @position = rotation * @position
  end

  def draw(direction)
    self.side(direction).draw
  end

end

class RCube

  def initialize
    @cubes = Set.new
    (-1..1).each {
      |x|
      (-1..1).each {
        |y|
        (-1..1).each {
          |z|
          @cubes << Cube.new(Vector[x, y, z])
        }
      }
    }
  [
    [:right, :red],
    [:left, :orange],
    [:up, :white],
    [:down, :yellow],
    [:front, :green],
    [:back, :blue]
  ].each {
    |key, value|
    direction = Direction[key]
    color = Color.new(value)
    self.face(direction).each {
      |cube|
      cube.set_color(direction, color)
    }
  }
  end

  def face(direction)
    @cubes.select {
      |cube|
      direction.inner_product(cube.position) == 1 
    }
  end

  def rotate_clockwise(face)
    rotation = Rotation[face]
    direction = Direction[face]
    self.face(direction).each {
      |cube|
      cube.rotate(rotation)
    }
  end

  def rotate_counterclockwise(face)
    3.times {
      self.rotate_clockwise(face)
    }
  end

  def rotate(face, reverse)
    if reverse
      self.rotate_counterclockwise(face)
    else
      self.rotate_clockwise(face)
    end
  end

  # ugly implementation, no absolute positioning when drawing in text mode
  def draw
    empty = Cube.new(Vector[0,0,0])
    cubes = Hash.new
    @cubes.each {
      |cube|
      cubes[cube.position.to_a] = cube
    }

    # up
    empty.draw(Direction[:up])
    empty.draw(Direction[:up])
    empty.draw(Direction[:up])
    cubes[[-1,  1, -1]].draw(Direction[:up])
    cubes[[ 0,  1, -1]].draw(Direction[:up])
    cubes[[ 1,  1, -1]].draw(Direction[:up])
    puts
    empty.draw(Direction[:up])
    empty.draw(Direction[:up])
    empty.draw(Direction[:up])
    cubes[[-1,  1,  0]].draw(Direction[:up])
    cubes[[ 0,  1,  0]].draw(Direction[:up])
    cubes[[ 1,  1,  0]].draw(Direction[:up])
    puts
    empty.draw(Direction[:up])
    empty.draw(Direction[:up])
    empty.draw(Direction[:up])
    cubes[[-1,  1,  1]].draw(Direction[:up])
    cubes[[ 0,  1,  1]].draw(Direction[:up])
    cubes[[ 1,  1,  1]].draw(Direction[:up])
    puts

    # left, front, right
    cubes[[-1,  1, -1]].draw(Direction[:left])
    cubes[[-1,  1,  0]].draw(Direction[:left])
    cubes[[-1,  1,  1]].draw(Direction[:left])
    cubes[[-1,  1,  1]].draw(Direction[:front])
    cubes[[ 0,  1,  1]].draw(Direction[:front])
    cubes[[ 1,  1,  1]].draw(Direction[:front])
    cubes[[ 1,  1,  1]].draw(Direction[:right])
    cubes[[ 1,  1,  0]].draw(Direction[:right])
    cubes[[ 1,  1, -1]].draw(Direction[:right])
    puts
    cubes[[-1,  0, -1]].draw(Direction[:left])
    cubes[[-1,  0,  0]].draw(Direction[:left])
    cubes[[-1,  0,  1]].draw(Direction[:left])
    cubes[[-1,  0,  1]].draw(Direction[:front])
    cubes[[ 0,  0,  1]].draw(Direction[:front])
    cubes[[ 1,  0,  1]].draw(Direction[:front])
    cubes[[ 1,  0,  1]].draw(Direction[:right])
    cubes[[ 1,  0,  0]].draw(Direction[:right])
    cubes[[ 1,  0, -1]].draw(Direction[:right])
    puts
    cubes[[-1, -1, -1]].draw(Direction[:left])
    cubes[[-1, -1,  0]].draw(Direction[:left])
    cubes[[-1, -1,  1]].draw(Direction[:left])
    cubes[[-1, -1,  1]].draw(Direction[:front])
    cubes[[ 0, -1,  1]].draw(Direction[:front])
    cubes[[ 1, -1,  1]].draw(Direction[:front])
    cubes[[ 1, -1,  1]].draw(Direction[:right])
    cubes[[ 1, -1,  0]].draw(Direction[:right])
    cubes[[ 1, -1, -1]].draw(Direction[:right])
    puts

    # down
    empty.draw(Direction[:down])
    empty.draw(Direction[:down])
    empty.draw(Direction[:down])
    cubes[[-1, -1,  1]].draw(Direction[:down])
    cubes[[ 0, -1,  1]].draw(Direction[:down])
    cubes[[ 1, -1,  1]].draw(Direction[:down])
    puts
    empty.draw(Direction[:down])
    empty.draw(Direction[:down])
    empty.draw(Direction[:down])
    cubes[[-1, -1,  0]].draw(Direction[:down])
    cubes[[ 0, -1,  0]].draw(Direction[:down])
    cubes[[ 1, -1,  0]].draw(Direction[:down])
    puts
    empty.draw(Direction[:down])
    empty.draw(Direction[:down])
    empty.draw(Direction[:down])
    cubes[[-1, -1, -1]].draw(Direction[:down])
    cubes[[ 0, -1, -1]].draw(Direction[:down])
    cubes[[ 1, -1, -1]].draw(Direction[:down])
    puts

    # back
    empty.draw(Direction[:back])
    empty.draw(Direction[:back])
    empty.draw(Direction[:back])
    cubes[[-1, -1, -1]].draw(Direction[:back])
    cubes[[ 0, -1, -1]].draw(Direction[:back])
    cubes[[ 1, -1, -1]].draw(Direction[:back])
    puts
    empty.draw(Direction[:back])
    empty.draw(Direction[:back])
    empty.draw(Direction[:back])
    cubes[[-1,  0, -1]].draw(Direction[:back])
    cubes[[ 0,  0, -1]].draw(Direction[:back])
    cubes[[ 1,  0, -1]].draw(Direction[:back])
    puts
    empty.draw(Direction[:back])
    empty.draw(Direction[:back])
    empty.draw(Direction[:back])
    cubes[[-1,  1, -1]].draw(Direction[:back])
    cubes[[ 0,  1, -1]].draw(Direction[:back])
    cubes[[ 1,  1, -1]].draw(Direction[:back])
    puts

  end

end
