(ns gcj2013.tictactoe)

;(defn update-rows
;  [index square rows]
;  (let [updates (rows-for-index index)]
;    ))
;
;(def rows-init
;  [nil nil nil nil nil nil nil nil nil nil])
;
;(defn get-rows
;  [board index rows]
;  (if (>= index (count board))
;    rows
;    (recur board (inc index) (update-rows index (nth board index) rows))))
;
;(defn full?
;  [board]
;  (not (contains? board \.)))
;
;(defn winner
;  [board]
;  (let [rows (get-rows board 0 (rows-init))
;        full (full? board)]
;    (if (contains? rows \X)
;      "X won"
;      (if (contains? rows \O)
;        "O won"
;        (if full
;          "Draw"
;          "Game has not completed")))))
;
;(defn board
;  [index boards]
;  (take 16 (take-last 17 (take (* (+ index 1) 17) boards))))
;
;(defn status
;  [boards index total]
;  (if (< index (count boards))
;    (print "Case #")
;    (print (+ index 1))
;    (print ": ")
;    (println (winner (board index boards)))
;    (recur boards (inc index) total)))
;
;(defn run
;  [input]
;  (let [total (first input)
;        boards (rest input)]
;    (status boards 0 total))


(defn update-row-status
  [status square]
  (cond
    (nil? status) square
    (= status \.) \.
    (= square \.) \.
    :default (case '(status square)
               '(\X \X) \X
               '(\X \O) \D
               '(\X \T) \X
               '(\O \O) \O
               '(\O \X) \D
               '(\O \T) \O
               '(\T \X) \X
               '(\T \O) \O)))

(defn row
  [index board]
  (case index
    0 (subs board 0 4)
    1 (subs board 5 9)
    2 (subs board 10 14)
    3 (subs board 15 19)
    4 (str (nth board 0) (nth board 5) (nth board 10) (nth board 15))
    5 (str (nth board 1) (nth board 6) (nth board 11) (nth board 16))
    6 (str (nth board 2) (nth board 7) (nth board 12) (nth board 17))
    7 (str (nth board 3) (nth board 8) (nth board 13) (nth board 18))
    8 (str (nth board 0) (nth board 6) (nth board 12) (nth board 19))
    9 (str (nth board 3) (nth board 7) (nth board 11) (nth board 15))))

(defn winner
  [states]
  (cond
    (contains? states \X) "X won"
    (contains? status \O) "O won"
    (contains? status \.) "Game has not completed"
    :default "Draw"))

(defn input
  [file]
  (clojure.string/replace (slurp file) "\n" ""))

(defn total
  [input]
  (Integer/parseInt (subs input 0 1)))

(defn boards
  [input]
  (partition 4 (partition 4 (rest input))))

(defn run
  [file]
  )

(defn sample
  []
  (run "res/tictactoe-sample.in"))

(defn small
  []
  (run "res/tictactoe-small.in"))

(defn large
  []
  (run "res/tictactoe-large.in"))

