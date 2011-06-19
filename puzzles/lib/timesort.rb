# BEWARE: highly fragile
#
# source: http://dis.4chan.org/read/prog/1295544154

class TimeSort

  def sort(array)
    sorted = Array.new
    threads = Array.new
    array.each do
      |n|
      threads << Thread.new { sleep(n); sorted << n; }
    end
    threads.each { |t| t.join }
    return sorted
  end

end
