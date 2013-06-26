(ns schedule.core-test
  (:require [clojure.test :refer :all]
            [schedule.core :refer :all]))

(defn test-total
  [teams schedule]
  (is (= (count schedule) (/ (* teams (dec teams)) 2))))

(defn test-complete
  ([teams schedule]
   (test-complete teams schedule 1 2))
  ([teams schedule team1 team2]
   (if (< team1 teams)
     (if (<= team2 teams)
       (is (some #{#{team1 team2}} schedule))
       (recur teams schedule (inc team1) (+ team1 2))))))

(defn test-team
  [size]
  (let [schedule (schedule size)]
    (test-total size schedule)
    (test-complete size schedule)))

(deftest does-not-exist
  (doseq [n [0 1 2 3 4]] (is (empty? (schedule n)))))

(deftest teams-small
  (doseq [n [6 7 8 9 10]] (test-team n)))

(deftest teams-big
  (doseq [n [23 57]] (test-team n)))
