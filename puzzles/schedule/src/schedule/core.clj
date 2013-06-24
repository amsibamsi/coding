(ns schedule.core)

(defn next-game
  ([games queue]
   (next-game games queue 0 1))
  ([games queue index1 index2]
   (let [max (- (count queue) 1)
         team1 (nth queue index1)
         team2 (nth queue index2)]
     (if (< index1 (- max 2))
       (if (< index2 (- max 1))
         (if (some #{#{team1 team2}} games)
           (recur games queue index1 (inc index2))
           [team1 team2])
         (recur games queue (inc index1) (+ index1 2)))
       nil))))

(defn requeue
  [queue game]
  (vec (concat (vec (remove (set game) queue)) game)))

(defn build
  [round total games queue]
  (if (= round total)
    (if (empty? games) nil games)
    (let [game (next-game games queue)]
      (if game
        (recur
          (inc round)
          total
          (conj games (set game))
          (requeue queue game))
        nil))))

(defn total-games
  [teams]
  (/ (* teams (- teams 1)) 2))

(defn queue
  [teams]
  (vec (range 1 (inc teams))))

(defn schedule
  "return the sequence of games for n teams"
  [n]
  (build 0 (total-games n) [] (queue n)))
