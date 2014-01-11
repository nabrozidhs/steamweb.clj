(ns steamweb.core-test
  (:require [clojure.test :refer :all]
            [steamweb.core :as steam]))

(deftest steamnews-test
  (testing "Valid Steam responses."
    (is (not (nil? (steam/news-app 570))))))
