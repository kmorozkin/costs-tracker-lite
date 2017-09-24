(ns costs-tracker-lite.resources
  (:require [clojure.java.io :as io]))

(defn read-resource!
  [resource]
  (-> resource io/resource slurp))