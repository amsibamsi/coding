require 'logger'

# search substrings containing a specific set of characters in some text
class SubString2

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
    hits = Hash.new
    @chars.each { |c| hits[c] = 0 }
    self.newChar(hits, 0)
    s = self.search(0, 0, hits)
    return (s == nil ? nil : s.join)
  end

  def search(left, right, hits)
    @log.debug("search: #{left}, #{right}")
    matches = Array.new
    # no match, grow to the right if possible
    if hits.value?(0) then
      return nil if right == @text.lastIndex
      self.newChar(hits, right + 1)
      matches << self.search(left, right + 1, hits)
    # found match, shrink from the left if possible
    else
      matches << @text[left..right]
      @log.debug("match")
      if left != right then
        self.oldChar(hits, left)
        matches << self.search(left + 1, right, hits)
      end
    end
    # smallest match is the result
    matches.delete(nil)
    matches.sort! { |a,b| a.length - b.length }
    return matches[0]
  end

  def newChar(hits, index)
    char = @text[index]
    hits[char] += 1 if hits.key?(char)
    @log.debug("hits: #{hits}")
  end

  def oldChar(hits, index)
    char = @text[index]
    hits[char] -=1 if hits.key?(char)
    @log.debug("hits: #{hits}")
  end

end

class Array

  def lastIndex
    return self.length - 1
  end

end
