(ns costs-tracker-lite.core
  (:require [clojure.java.jdbc :as jdbc]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.adapter.jetty :refer [run-jetty]]
            [costs-tracker-lite.resources :refer [read-resource!]]
            [costs-tracker-lite.db :refer [c3p0]])
  (:gen-class))

(defroutes
  ring-handler
  (GET "/" [] (read-resource! "index.html"))
  (POST "/insert" {body :body} (slurp body))
  (GET "/test" []
    (jdbc/query @c3p0 ["SELECT * FROM Tags"]))
  (GET "/:path{.*\\.js}" [path] (println path) (read-resource! path))
  (route/not-found "Page not found!"))

(defn go! []
  (run-jetty ring-handler {:port 8080}))

(defn -main [] (go!))