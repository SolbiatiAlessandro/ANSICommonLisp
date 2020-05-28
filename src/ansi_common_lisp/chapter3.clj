(ns ansi-common-lisp.chapter3)

;; (compress_merge_number_number 11 12)
;; (11 12)
;; (compress_merge_number_number 11 11)
;; ((2 11))
(defn compress_merge_number_number [a b]
  (if (= a b)
    (list (list 2 a))
    (list a b)))

;; (compress_merge_tuple_number `(1 11) 11)
;; ((2 11))
;; (compress_merge_tuple_number `(1 11) 12)
;; ((1 11) 12)
(defn compress_merge_tuple_number [a b]
  (if (= (first (rest a)) b)
    (list (cons (+ 1 (first a)) (rest a)))
    (list a b)))

;; compress list
;; (compress `(2 2 2 3 3 2 2 2 2 1 2 2))
;; ((3 2) (2 3) (4 2) 1 (2 2))
(defn compress [lst]
  (if (= (count lst) 1)
    lst
    (let [a (first lst)
          b (first (rest lst))
          c (rest (rest lst))
          compressed_lst (if (seq? a)
                           (concat (compress_merge_tuple_number a b) c)
                           (concat (compress_merge_number_number a b) c))]
      (if (= compressed_lst lst)
        (cons (first lst) (compress (rest lst)))
        (compress compressed_lst)))))
