#!/usr/bin/env ruby

jobs = Queue.new
results = Queue.new
num_threads = 10
threads = []
sem = Mutex.new

for i in 1..num_threads do
  threads << Thread.new do
    loop do
      j = jobs.pop
      if j
        sleep 0.3
        sem.synchronize do
          puts "#{Thread.current.object_id}: #{j}"
        end
        if !results.closed?
          results.push j
        end
      else
        break
      end
    end
  end
end

threads << Thread.new do
  loop do
    r = results.pop
    if r
      jobs.push(r+1)
    else
      jobs.close
      break
    end
  end
end

for i in 0..9 do
  jobs.push i
end

sleep 5

results.close
threads.each do |t|
  t.join
end
