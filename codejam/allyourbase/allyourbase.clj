; google code jam 2009 round 1c

(use 'clojure.java.io)
(use 'clojure.string)

; the minimum value of a number is when the first symbol from left to right
; has value 0, second new symbol is 1, third new is 2, etc.
; exception: number has no leading zeroes, so first symbol is 1 and second is 0
; example: a8a = 101 in binary = 5 in decimal
(defn minimum-alphabet [number alphabet]
  (if (empty? number)
    alphabet 
    (let
      [symbol (first number)
       size (count alphabet)
       added (if (nil? (alphabet symbol))
             (case size
               0 {symbol 1}
               1 {symbol 0}
               {symbol size})
             {})]
      (minimum-alphabet (rest number) (merge alphabet added)))))

(defn minimum [number alphabet sum]
  (if (empty? number)
    (long sum)
    (let
      [digit (first number)
       value (alphabet digit)
       ; exception: base is not unary
       base (max 2 (count alphabet))]
      (minimum (rest number) alphabet (+ (* sum base) value)))))

(defn parse [messages current total]
  (if (<= current total)
    (let
      [number (seq (first messages))
       minimum (minimum number (minimum-alphabet number {}) 0)]
      (println (str "Case #" current ": " minimum))
      (parse (rest messages) (+ current 1) total))
    nil))

(def input (line-seq (java.io.BufferedReader. *in*)))
(parse (rest input) 1 (read-string (first input)))
