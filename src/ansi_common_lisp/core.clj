(ns ansi-common-lisp.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Helloa, World!"))

(defn test [x]
  (+ x 4))

(+ 1 1)

(list 1 2 3)

(list `(+ 1 2) (+ 1 3))

;;lists
(cons `a `(b c d))
(first `(a b c d))
(rest `(a b c d))
(rest (list 1 2 3))
(first (list 1 2 3))

;; predicates
(list? (list 1 2))
(list? 27)

;; if
(if (list? (list 1 2))
  (+ 1 2)
  (+ 1 3))

;;functions
(defn my-third [x]
  (first (rest (rest x))))

;;recursion
(defn my-member [obj lst]
  (if (empty? lst)
    nil
    (if (= obj (first lst))
      true
      (is-member obj (rest lst)))))
