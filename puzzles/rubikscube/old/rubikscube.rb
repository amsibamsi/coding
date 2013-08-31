class Cube

  def initialize
    @front = Side.new(Color.white)
    @right = Side.new(Color.green)
    @left = Side.new(Color.blue)
    @top = Side.new(Color.red)
    @down = Side.new(Color.orange)
    @back = Side.new(Color.yellow)
    @invisible = Side.new(Color.transparent)
    @lines = [
      [@invisible, @top],
      [@left,@front,@right,@back],
      [@invisible,@down],
      [@invisible,@back],
    ]
  end

  def draw
    @lines.each {
      |line|
      3.times.each {
        |row|
        line.each {
          |side|
          side.drawRow(row)
          print Color.transparent
        }
        puts
        puts
      }
    }
  end

  def rotateFront

  end

end

class Side

  def initialize(color)
    @fields = Array.new(9,color)
  end

  def drawRow(row)
    offset = row * 3 % 9
    print("#{@fields[offset+0]}  #{@fields[offset+1]}  #{@fields[offset+2]}")
  end

  def draw
    self.drawRow(0)
    puts
    self.drawRow(1)
    puts
    self.drawRow(2)
    puts
  end

end


