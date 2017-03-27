(ns costs-tracker-lite.core
  (:require [clojure.java.io :as io]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.adapter.jetty :refer [run-jetty]])
  (:gen-class))

(defn read-resource
  [resource]
  (-> resource io/resource slurp))

(defroutes
  ring-handler
  (GET "/" [] (read-resource "index.html"))
  (route/not-found "Page not found!"))

(defn -main
  []
  (run-jetty ring-handler {:port 8080}))