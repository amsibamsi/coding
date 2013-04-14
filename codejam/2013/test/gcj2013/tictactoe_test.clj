(ns gcj2013.tictactoe-test
  (:use clojure.test)
  (:use gcj2013.tictactoe))

(deftest test-sample
  (testing "sample output")
  (is (= (sample) (slurp "res/tictactoe-sample.out"))))

(deftest test-small
  (testing "small output")
  (is (= (small) (slurp "res/tictactoe-small.out"))))

(deftest test-large
  (testing "large output")
  (is (= (large) (slurp "res/tictactoe-large.out"))))
