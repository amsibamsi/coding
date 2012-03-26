; google code jam 2009 round 1c

(use 'clojure.contrib.generic.math-functions)

(def message (seq (first *command-line-args*)))

(def alphabet (distinct message))

(def base (count alphabet))

(defn minimum [input sum]
  (if (empty? input)
    sum
    (let
      [sym (first input)
       digit (.indexOf alphabet sym)
       position (count input)
       value (* digit (pow base position))]
      (println sum)
      (minimum (rest input) (+ sum value)))))

(println (minimum message 0))
