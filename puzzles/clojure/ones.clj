(defn ones-count
  "how many times the digit '1' appears in an integer"
  [n]
  (if (= n 0)
    0
    (+
      (if (= 1 (mod n 10)) 1 0)
      (ones-count (quot n 10)))))

(defn ones-change
  "change in the amount of the digit '1' appearing in an integer and its increment"
  [n]
  (case (mod n 10)
    0 1
    1 -1
    9 (ones-change (quot n 10))
    0))

(defn ones-changes-upto
  "ones-change for all integers from 0 to n-1"
  ([n] (ones-changes-upto 0 [] n))
  ([n changes max]
   (if (= n max)
     changes
     (ones-changes-upto
       (inc n)
       (conj changes (ones-change n))
       max))))

(defn ones-upto
  "number of times the char '1' appears for all integers from 1 to his"
  ([n] (ones-upto [0] (ones-changes-upto n)))
  ([ones changes]
   (if (empty? changes)
     ones
     (ones-upto
       (conj ones (+ (last ones) (first changes)))
       (rest changes)))))

(defn ones-count-upto
  "number of times the char '1' appears when writing down all integers between 1 and this"
  [n]
  (reduce + (ones-upto n)))
