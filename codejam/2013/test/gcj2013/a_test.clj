(ns gcj2013.a-test
  (:use clojure.test)
  (:use gcj2013.a))

(deftest test-sample
  (testing "sample output")
  (is (= (sample) (slurp "res/a-sample.out"))))
