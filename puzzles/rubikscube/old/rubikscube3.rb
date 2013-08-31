class Cube

end

class Side

  def initialize(color,orientation)
    @fields = Hash[
      "0",color,
      "1",color,
      "2",color,
      "3",color,
      "4",color,
      "5",color,
      "6",color,
      "7",color,
      "8",color,
    ]
    @orientation = orientation
  end

  def rotate(axis)
    case axis
    when 0
      
    when 1

    when 2

    end
  end

end
