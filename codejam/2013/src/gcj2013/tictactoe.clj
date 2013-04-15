(ns gcj2013.tictactoe)

(defn select
  [col indices]
  (map (fn [x] (nth col x)) indices))

(defn update-row
  [state square]
  (cond
    (nil? state) square
    (= state \.) \.
    (= square \.) \.
    (= state \D) \D
    (= square \D) \D
    :default (case (str state square)
               "XX" \X
               "XO" \D
               "XT" \X
               "OO" \O
               "OX" \D
               "OT" \O
               "TX" \X
               "TO" \O)))

(defn row-state
  [row]
  (reduce update-row row))

(defn rows
  [board]
  (map (fn [x] (select board x)) [(range 0 4)
                                  (range 4 8)
                                  (range 8 12)
                                  (range 12 16)
                                  (range 0 16 4)
                                  (range 1 16 4)
                                  (range 2 16 4)
                                  (range 3 16 4)
                                  (range 0 16 5)
                                  (range 3 13 3)]))

(defn row-states
  [board]
  (map row-state (rows board)))

(defn board-state
  [states]
  (first
    (concat
      (filter (fn [x] (or (= x \X) (= x \O))) states)
      (filter (fn [x] (= x \.)) states)
      '(\D))))

(defn winner
  [board]
  (let [states (row-states board)
        state (board-state states)]
    (case state
      \X "X won"
      \O "O won"
      \. "Game has not completed"
      \D "Draw")))

(defn winners
  ([boards] (winners boards 0 ""))
  ([boards index result]
    (if (< index (count boards))
      (let [w (winner (nth boards index))
            i (inc index)]
          (recur
            boards
            (inc index)
            (str result "Case #" i ": " w \newline)))
      result)))

(defn input
  [file]
  (clojure.string/replace
    (clojure.string/join
      ""
      (rest
        (clojure.string/split
          (slurp file)
          #"\n")))
    "\n"
    ""))

(defn boards
  [input]
  (partition 16 input))

(defn run
  [file]
  (winners (boards (input file))))

(defn sample
  []
  (run "res/tictactoe-sample.in"))

(defn small
  []
  (run "res/tictactoe-small.in"))

(defn large
  []
  (run "res/tictactoe-large.in"))

(defn main-sample
  []
  (print (sample)))

(defn main-small
  []
  (print (small)))

(defn main-large
  []
  (print (large)))

(defn -main
  []
  (println "sample:")
  (println)
  (main-sample)
  (println)
  (println "small:")
  (println)
  (main-small)
  (println)
  (println "large:")
  (println)
  (main-large))
