class RCube

  def initialize
    @top = [[0,0,0],[0,0,0],[0,0,0]]
    @left = [[1,1,1],[1,1,1],[1,1,1]]
    @front = [[2,2,2],[2,2,2],[2,2,2]]
    @right = [[3,3,3],[3,3,3],[3,3,3]]
    @down = [[4,4,4],[4,4,4],[4,4,4]]
    @back = [[5,5,5],[5,5,5],[5,5,5]]
  end

  def turnSide(side)
     return [
      [side[2][0],side[1][0],side[0][0]],
      [side[2][1],side[1][1],side[0][1]],
      [side[2][2],side[1][2],side[0][2]],
    ]
  end

  def turnOnFront
   @front = self.turnSide(@front) 

  end

end
