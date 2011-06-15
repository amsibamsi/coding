require 'logger'

# search substrings containing a specific set of characters in some text
class SubString

  # initialize(string, string)
  def initialize(text, chars)
    @text = text.split(//)
    @chars = chars.split(//).uniq
    @log = Logger.new(STDOUT)
    @log.level = Logger::ERROR
  end

  def debug
    @log.level = Logger::DEBUG
  end

  def shortest
    # start from left with minimum length
    return self.search(0, @chars.length - 1).to_s
  end

  def search(left, right)
    @log.debug("search: #{left},#{right}")
    length = right - left + 1
    s = @text[left,length] 
    @log.debug("substring: #{s}")
    return nil if right > @text.lastIndex
    if not self.match?(s)
      if right == @text.lastIndex
        # grow window, start over from left
        s = self.search(0, length)
      else
        # move window to the right
        s = self.search(left + 1, right + 1)
      end
    end
    return s
  end

  def match?(substring)
    match = true
    @chars.each do
      |c|
      match = false if not substring.include?(c)
    end
    @log.debug("match: #{match}")
    return match
  end

end

class Array

  def lastIndex
    return self.length - 1
  end

end
