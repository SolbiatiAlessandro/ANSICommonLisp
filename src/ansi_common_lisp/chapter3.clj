;; a bunch of run-length encoding algorithm implementations
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
;; (alex_compress `(2 2 2 3 3 2 2 2 2 1 2 2))
;; ((3 2) (2 3) (4 2) 1 (2 2))
(defn alex_compress [lst]
  (if (= (count lst) 1)
    lst
    (let [a (first lst)
          b (first (rest lst))
          c (rest (rest lst))
          compressed_lst (if (seq? a)
                           (concat (compress_merge_tuple_number a b) c)
                           (concat (compress_merge_number_number a b) c))]
      (if (= compressed_lst lst)
        (cons (first lst) (alex_compress (rest lst)))
        (alex_compress compressed_lst)))))

;; FAILED ATTEMPT AT SHORTEST PATH
(defn cl-print [x] (doto x (print)))

(defn next_node_from_paths [paths]
  ((first (apply min-key second paths))))

;; (shortest_path :a :d {:a `(:a :d)}
;; (:a :d)
(defn shortest_path [start end graph]
  (do
    (println start end)
    (if (= start end)
      (list end)
      (let [children (get graph start)
            paths (map list children
                       (map inc
                            (paths_from_children children end graph)))
            a (cl-print paths)
            next_node (next_node_from_paths paths)]
        (cons
         (next_node)
         (shortest_path next_node end graph))))))

(defn paths_from_children [children end graph]
  (let [len (count children)
        args (map list children (repeat len end) (repeat len graph))]
    (map (fn [args] (apply shortest_path args)) args)))

;; RETRYING AFTER HAVING LOOKED AT SOLUTION
(defn new_nodes [node value graph]
  (let [children (get graph node)]
    (map (fn [child] (cons child graph)) children)))

(defn bfs [end queue graph]
  (if (empty? queue)
    nil
    (let [path (first queue)
          node (first path)]
      (if (= node end)
        (reverse path)
        (bfs end
             (concat (rest queue) (new_nodes node path graph))
             graph)))))

;; (shortest_path :a :d {:a `(:g :d)})
;; 1
;; (shortest_path :a :d {:a `(:b :c) :b `(:c) :c `(:d)})
;; 2
(defn shortest_path [start end graph]
  (bfs end (list (list start)) graph))
