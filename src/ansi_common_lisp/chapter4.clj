(ns ansi-common-lisp.chapter4)

;; binary search
;; (bin-search 3 (vector  1 2 3 4 5))
;; 3
(defn finder [start end target lst]
  (let [middle (/ (+ start end) 2)]
    (cond
      (= (get lst middle) target) middle
      (> (get lst middle) target) (finder (+ middle 1) end target lst)
      (< (get lst middle) target) (finder start (- middle 1) target lst))))

(defn bin-search [lst target]
  (finder 0 (- (count lst) 1) target lst))

;; structs
(defrecord Point [x y])
(get (Point. 2 3) :x)
(assoc (Point. 2 3) :x 4)
(Point? (Point. 2 3))