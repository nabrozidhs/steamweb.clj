(ns steamweb.util-test
  (:require [clojure.test :refer :all]
            [steamweb.util :as util]))

(deftest serialise-args-test
  (testing "serialise-args function."
    (is (= "" (util/serialise-args {})))
    (is (= "key=value" (util/serialise-args {"key" "value"})))
    (is (= "a=b&c=d" (util/serialise-args {"a" "b"
                                           "c" "d"})))))
