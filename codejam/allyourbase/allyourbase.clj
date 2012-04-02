; google code jam 2009 round 1c

(use 'clojure.java.io)
(use 'clojure.string)

; the minimum value of a number is when the first symbol from left to right
; has value 0, second new symbol is 1, third new is 2, etc.
; exception: number has no leading zeroes, so first symbol is 1 and second is 0
; example: a8a = 101 in binary = 5 in decimal
(defn minimum-value [digit alphabet]
  (let [value (.indexOf alphabet digit)]
    (if (== value 0)
      1
      (if (== value 1)
        0
        value))))

(defn minimum-decimal [number alphabet base sum]
  (if (empty? number)
    (long sum)
    (let
      [digit (first number)
       value (minimum-value digit alphabet)
       magnitude (- (count number) 1)
       decimal (* value (Math/pow base magnitude))]
      (minimum-decimal (rest number) alphabet base (+ sum decimal)))))

(defn minimum [message]
  (let
    [symbols (seq message)
     alphabet (distinct symbols)
     ; exception: base 1 not allowed
     base (max 2 (count alphabet))]
    (str (minimum-decimal symbols alphabet base 0))))

(defn parse [messages counter amount output]
  (if (<= counter amount)
    (parse
      (rest messages)
      (+ counter 1)
      amount
      (conj output (str "Case #" counter ": " (minimum (first messages)))))
    output))

(defn parse-file [in out]
  (let
    [input (split-lines (slurp in))
     amount (read-string (first input))
     messages (rest input)
     result (parse messages 1 amount [])]
    (spit out (str (join "\n" result) "\n"))))

(parse-file "small.in" "small.out")
(parse-file "large.in" "large.out")
