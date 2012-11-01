class HashSort

  def sort(array)
    h = Hash.new
    max = 0
    x = Array.new
    array.each do
      |n|
      h[n] = n
      max = n if n > max
    end
    (0..max).each do
      |n|
      x << h[n] if not h[n].nil?
    end
    return x
  end

end
