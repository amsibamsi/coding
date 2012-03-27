; google code jam 2009 round 1c

(use 'clojure.contrib.generic.math-functions)
(use 'clojure.java.io)

; first character is not a zero
(defn significance [sym alphabet]
  (let [index (.indexOf alphabet sym)]
    (if (== index 0)
      1
      (if (== index 1)
        0
        index))))

(defn find-minimum [input alphabet base sum]
  (if (empty? input)
    sum
    (let
      [sym (first input)
       digit (significance sym alphabet)
       position (- (count input) 1)
       value (* digit (pow base position))]
      (find-minimum (rest input) alphabet base (+ sum value)))))

(defn minimum [message]
  (let [symbols (seq message)
        alphabet (distinct symbols)
        base (count alphabet)]
    (find-minimum symbols alphabet base 0)))

(with-open [rdr (reader "A-small-practice.in")]
  (doseq [message (line-seq rdr)]
    (println (minimum message))))
