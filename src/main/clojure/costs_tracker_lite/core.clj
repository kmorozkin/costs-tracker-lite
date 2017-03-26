(ns costs-tracker-lite.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.adapter.jetty :refer [run-jetty]]))

(defroutes
  ring-handler
  (GET "/" [] "Hello world!")
  (route/not-found "Page not found!"))

(run-jetty ring-handler {:port 8081})