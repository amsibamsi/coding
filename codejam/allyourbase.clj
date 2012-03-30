; google code jam 2009 round 1c

(use 'clojure.java.io)
(use 'clojure.string)

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
    (int sum)
    (let
      [sym (first input)
       digit (significance sym alphabet)
       position (- (count input) 1)
       value (* digit (Math/pow base position))]
      (find-minimum (rest input) alphabet base (+ sum value)))))

(defn minimum [message]
  (let [symbols (seq message)
        alphabet (distinct symbols)
        base (count alphabet)]
    (find-minimum symbols alphabet base 0)))

(defn output [messages counter]
  (if (empty? messages)
    nil
    (do
      (print (str "Case #" counter ": "))
      (println (minimum (first messages)))
      (output (rest messages) (+ counter 1)))))

(def msg (rest (split-lines (slurp "A-small-practice.in"))))
(output msg 1)
