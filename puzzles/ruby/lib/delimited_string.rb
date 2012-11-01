module DelimitedString

  def DelimitedString.delimited_string(array, delimiter, escape)
    string = ''
    array.each do |element|
      string = string + element.gsub(/#{escape}/, escape * 2).gsub(/#{delimiter}/, escape + delimiter) + delimiter
    end
    return string
  end

  def DelimitedString.arrayed_string(string, delimiter, escape)
    array = []
    word = ''
    lastChar = ''
    string.scan(/./).each do |char|
      syl = lastChar + char
      if syl =~ /#{delimiter}./
        array = array + [word]
        word = ''
        lastChar = char
      elsif syl =~ /[^#{escape}]#{delimiter}/
        word = word + lastChar
        array = array + [word]
        word = ''
        lastChar = ''
      elsif syl =~ /#{escape}#{escape}/
        word = word + escape
        lastChar = ''
      elsif syl =~ /#{escape}#{delimiter}/
        word = word + delimiter
        lastChar = ''
      elsif syl =~ /../
        word = word + lastChar
        lastChar = char
      else
        lastChar = char
      end
      char = ''
    end
    if lastChar =~ /#{delimiter}/
      array = array + [word]
    end
    return array
  end

end
