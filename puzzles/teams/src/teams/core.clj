(ns teams.core)

(schedule
  "return the sequence of games for n teams"
  [n]
  (build 0 (total-games n) [] (range 1 n)))

(build
  "build schedule"
  [round total games lfu]
  ; todo ...
  )
