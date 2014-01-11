(ns steamweb.client-test
  (:require [clojure.test :refer :all]
            [steamweb.client :as client]))

(deftest client-slurp-json-test
  (testing "Valid Steam responses."
    (is (not (nil? (client/slurp-json "http://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/?appid=440&count=3&maxlength=300&format=json")))))
  
  (testing "Missing key steam responses"
    (is (nil? (client/slurp-json "http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=XXXXXXXXXXXXXXXXXXXXXXX&steamids=76561197960435530")))))
