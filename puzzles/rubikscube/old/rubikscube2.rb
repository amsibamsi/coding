require 'matrix'

class RCube

  # rubik's cube adressing (opened cube):
  #
  #          t0 t1 t2
  #          t3 t4 t5
  #          t6 t7 t8
  # l0 l1 l2 f0 f1 f2 r0 r1 r2
  # l3 l4 l5 f3 f4 f5 r3 r4 r5
  # l6 l7 l8 f6 f7 f8 r6 r7 r8
  #          d0 d1 d2
  #          d3 d4 d5
  #          d6 d7 d8
  #          b0 b1 b2
  #          b3 b4 b5
  #          b6 b7 b8
  #
  # t: top
  # l: left
  # f: front
  # r: right
  # d: down
  # b: back
  #
  def initialize
    @front = Matrix[[0,0,0],[0,0,0],[0,0,0]]
    @right = Matrix[[1,1,1],[1,1,1],[1,1,1]]
    @left = Matrix[[2,2,2],[2,2,2],[2,2,2]]
    @top = Matrix[[3,3,3],[3,3,3],[3,3,3]]
    @down = Matrix[[4,4,4],[4,4,4],[4,4,4]]
    @back = Matrix[[5,5,5],[5,5,5],[5,5,5]]
  end

  def rotateFront
    @front = Matrix.columns([
      @front.row(2),
      @front.row(1),
      @front.row(0),
    ])
    topNew = Matrix.rows([
      @top.row(0),
      @top.row(1),
      @left.column(2).to_a.reverse,
    ])
    rightNew = Matrix.columns([
      @top.row(2),
      @right.column(1),
      @right.column(2),
    ])
    downNew = Matrix.rows([
      @right.column(0).to_a.reverse,
      @down.row(1),
      @down.row(2),
    ])
    leftNew = Matrix.columns([
      @left.column(0),
      @left.column(1),
      @down.row(0),
    ])
    @top = topNew
    @right = rightNew
    @down = downNew
    @left = leftNew
  end

end

trap("INT") {
  puts
  exit 0
}

#while true
#  input = STDIN.gets
#  puts "-> #{input}"
#end
