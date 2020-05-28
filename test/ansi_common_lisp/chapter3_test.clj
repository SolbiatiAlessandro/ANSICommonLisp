(ns ansi-common-lisp.chapter3-test
  (:require [clojure.test :refer :all]
            [ansi-common-lisp.chapter3 :refer :all]))

(deftest compress-test
  (testing "compress"
    (is (= (compress `(1 1 1 0 1 0 0 0 0 1)) `((3 1) 0 1 (4 0) 1)))))
