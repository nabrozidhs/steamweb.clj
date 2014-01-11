(ns steamweb.core-test
  (:require [clojure.test :refer :all]
            [steamweb.core :as steam]))

(deftest steamnews-tests
  (testing "Valid SteamNews responses."
    (is (not (nil? (steam/news-app 570))))))

(deftest steamuserstats-tests
  (testing "Valid SteamUserStats responses."
    (is (not (nil? (steam/global-achievement-percentages-for-app 440))))))
