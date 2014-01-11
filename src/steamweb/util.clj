(ns steamweb.util
  (:require [steamweb.client :as client]
            [steamweb.util :as util]
            [clojure.string :as string]))

(defn serialise-args
  "Converts map to http name value pairs."
  [args]
  (string/join "&"
               (map #(string/join "=" %) (seq args))))
