(ns costs-tracker-lite.strings
  (:require [clojure.string :as s]))

(defn format
  [string & values]
  (reduce
    (fn [current [str-index item]] (s/replace current str-index item))
    string
    (map-indexed (fn [i item] [(s/join ["{" (str i) "}"]) item]) values)))