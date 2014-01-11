(ns steamweb.client
  (:require [clj-http.client :as client]))

(defn slurp-json
  "Returns the contents of the url pointing to a json."
  [url]
  (let [response (client/get url {:headers {"User-Agent" "steamapi.clj"}
                                  :as :json
                                  :throw-exceptions false})]
    (if (= 200 (:status response))
      (:body response)
      nil)))
