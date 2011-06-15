def multiples35(max)
  (1...max).select do |n|
    n % 3 == 0 or n % 5 == 0
  end
end

class Array
  def sum
    self.inject{|sum,x| sum + x }
  end
end

def multiples35_recursive(m3, m5, max)
  return [] if m3 >= max and m5 >= max
  if m3 == m5
    return multiples35_recursive(m3 + 3, m5 + 5, max) + [m3]
  elsif m3 < m5
    return multiples35_recursive(m3 + 3, m5, max) + [m3]
  else
    return multiples35_recursive(m3, m5 + 5, max) + [m5]
  end
end
