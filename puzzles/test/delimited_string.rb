require 'test/unit'
require 'delimited_string'

class DelimitedStringTest < Test::Unit::TestCase

  D = ',' # Delimiter
  E = '#' # Escape character

  def delimited(array)
    DelimitedString.delimited_string(array, D, E)
  end

  def arrayed(string)
    DelimitedString.arrayed_string(string, D, E)
  end

  def test_delimited_normal
    assert_equal "abc#{D}def#{D}", delimited(['abc', 'def'])
  end

  def test_delimited_with_delimiter
    assert_equal "ab#{E}#{D}c#{D}def#{D}", delimited(["ab#{D}c", 'def'])
  end

  def test_delimited_with_escape
    assert_equal "abc#{D}d#{E}#{E}ef#{D}", delimited(['abc', "d#{E}ef"])
  end

  def test_delimited_with_delimiter_and_escape
    assert_equal "abc#{D}d#{E}#{D}#{E}#{E}ef#{D}", delimited(['abc', "d#{D}#{E}ef"])
  end

  def test_arrayed_normal
    assert_equal ['abc', 'def'], arrayed("abc#{D}def#{D}")
  end

  def test_arrayed_with_delimiter
    assert_equal ["a#{D}bc", 'def'], arrayed("a#{E}#{D}bc#{D}def#{D}")
  end

  def test_arrayed_with_escape
    assert_equal ["#{E}abc", 'def'], arrayed("#{E}#{E}abc#{D}def#{D}")
  end

  def test_arrayed_with_delimiter_and_escape
    assert_equal ["#{E}abc", "#{D}def"], arrayed("#{E}#{E}abc#{D}#{E}#{D}def#{D}")
  end

  def test_complex
    s="#{D}#{D}#{E}#{E}#{E}#{D}#{E}#{D}#{D}#{E}#{D}#{D}#{E}#{E}#{D}"
    assert_equal s, delimited(arrayed(s))
  end

  def test_empty_string
    assert_equal [], arrayed('')
  end

  def test_empty_array
    assert_equal '', delimited([])
  end

  def test_multiple_empty_strings
    assert_equal ['', '', ''], arrayed("#{D}#{D}#{D}")
  end

  def test_multiple_empty_arrays
    assert_equal "#{D}#{D}#{D}#{D}", delimited(['', '', '', ''])
  end

end
