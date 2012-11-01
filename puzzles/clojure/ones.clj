(def ones
  (memoize
    (fn [number]
      (if (= number 0)
        0
        (+
          (if (= 1 (mod number 10)) 1 0)
          (ones (quot number 10)))))))

(def ones-upto
  (memoize
    (fn [number]
      (if (= number 1)
        1
        (+
          (ones number)
          (ones-upto (dec number)))))))

(defn fixpoint? [number]
  (= number (ones-upto number)))

(defn fixpoints
  ([amount] (fixpoints 1 [] amount))
  ([number found amount]
   (if (= amount 0)
     found
     (let [is-fixpoint (fixpoint? number)]
       (fixpoints
         (inc number)
         (if is-fixpoint (conj found number) found)
         (if is-fixpoint (dec amount) amount))))))

;(defn find-fixpoints
; ([amount] (find-fixpoints 1 0 amount []))
; ([number allones-last amount fixpoints]
; (if (= amount 0)
;   fixpoints
;   ((let [allones (+ allones-last (ones number))
;          is-fixpoint (= allones number)
;          number-next (inc number)
;          amount-next (if is-fixpoint (dec amount) amount)
;          fixpoints-next (if is-fixpoint (conj fixpoints number) fixpoints) ]
;      (find-fixpoints number-next allones amount-next fixpoints-next))))))
