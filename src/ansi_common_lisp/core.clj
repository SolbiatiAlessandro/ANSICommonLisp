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

;; let
(let [x 1 y 2] (+ x y))

;; def
(def x 2)
(+ x x)

;; iteration
(dotimes [i 3]
  (println i))

(doseq [i `(1 4 3)]
  (println i))

;;higher order function
(defn double-op [f a b]
  (* 2 (f a b)))

(double-op + 1 2)

(defn add-two [x y]
  (+ x y 2))

(double-op add-two 1 2)

;;anonymous functions
(double-op (fn [x y] (+ x y 3)) 1 2)
(double-op #(+ %1 %2 4) 1 2)
