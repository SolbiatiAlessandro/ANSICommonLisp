(ns ansi-common-lisp.chapter4-test
  (:require [clojure.test :refer :all]
            [ansi-common-lisp.chapter4 :refer :all]))

(deftest binsearch-test
  (testing "bin-search"
    (is (= (bin-search (vector 1 2 3 4 5) 3) 2))))
