require 'logger'

class MapReduce

  def initialize(documents)
    @documents = documents
    @log = Logger.new(STDOUT)
    @log.level = Logger::ERROR
  end

  def debug
    @log.level = Logger::DEBUG
  end

  # count occurences of words in a set of documents
  def map(documents)
    count = Hash.new
    documents.each do
      |document|
      document.each do
        |word|
        if count[word].nil? then
          count[word] = 1
        else
          count[word] += 1
        end
      end
    end
    @log.debug("mapped: #{count}")
    return count
  end

  # count together word counts
  def reduce(counts)
    total = Hash.new
    counts.each do
      |count|
      count.each_pair do
        |word, count|
        if total[word].nil? then
          total[word] = count
        else
          total[word] += count
        end
      end
    end
    @log.debug("reduced: #{total}")
    return total
  end

  def mapreduce(mapperCount, reducerCount)
    documentsPerMapper = @documents.size.fdiv(mapperCount).ceil
    mappers = Array.new
    reducers = Array.new
    
    # spread documents to mappers
    documentIndex = 0
    while mappers.size < mapperCount and documentIndex < @documents.size do
      mappers << Thread.new { self.map(@documents[documentIndex,documentsPerMapper]) }
      documentIndex += documentsPerMapper
    end
    
    # sort outputs of mappers according to the hash of the words,
    # so that counts for the same word will always got to the same reducer
    #
    # this code here is just for the ease of implementation,
    # the functionality should also be distributed to mappers/reducers
    sortedCounts = Array.new
    reducerCount.times { sortedCounts << Array.new }
    mappers.each do
      |mapper|
      mapper.join
      mapper.value.each_pair do
        |key, value|
        reducer = key.hash.abs % reducerCount
        sortedCounts[reducer] << {key,value}
      end
    end
    sortedCounts.each do
      |counts|
      reducers << Thread.new { self.reduce(counts) }
    end

    # compute the total from all reducer outputs,
    # can assume that there are no overlapping counts from different reducers
    total = Hash.new
    reducers.each do
      |reducer|
      reducer.join
      total.merge!(reducer.value)
    end

    return total
  end

end
