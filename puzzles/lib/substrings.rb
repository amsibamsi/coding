# find substrings in a text containing a specific set of characters
class SubStrings

  # initialize(string, string)
  def initialize(text, chars)
    @text = text.split(//)
    @chars = chars.split(//).uniq
  end

  def shortest
    return self.find_shortest([0, @chars.length - 1], @text.length)
  end

  def find_shortest(window, maxLength)
    length = window[1] - window[0]
    return nil if length > maxLength
    return nil if length < @chars.length
    x = self.match(@text[window[0],window[1]])
    if not x.nil?
      x = self.find_shortest([0, length - 2], length - 1)
    else
      x = self.find_shortest([window[0] + 1, window[1] + 1], maxLength)
      if x.nil?
        x = self.find_shortest([0, length + 1], maxLength)
      end
    end
    return x
  end

  def match(substring)
    match = true
    @chars.each do
      |c|
  end

end
