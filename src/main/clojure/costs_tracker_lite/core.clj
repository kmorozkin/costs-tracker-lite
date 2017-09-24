(ns costs-tracker-lite.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.adapter.jetty :refer [run-jetty]]
            [costs-tracker-lite.resources :refer [read-resource!]]
            [clojure.java.jdbc :as jdbc])
  (:import (com.mchange.v2.c3p0 ComboPooledDataSource))
  (:gen-class))

(def spec
    {:classname "org.postgresql.Driver"
     :subprotocol "postgresql"
     :subname "//localhost:5432/costs-tracker"
     :user "test"
     :password "test"})

(defn spec->pool
  [spec]
  (let [datasource (ComboPooledDataSource.)]
    (doto datasource
      (.setDriverClass (:classname spec))
      (.setJdbcUrl (str "jdbc:" (:subprotocol spec) ":" (:subname spec)))
      (.setUser (:user spec))
      (.setPassword (:password spec))
      ;; expire excess connections after 30 minutes of inactivity:
      (.setMaxIdleTimeExcessConnections (* 30 60))
      ;; expire connections after 3 hours of inactivity:
      (.setMaxIdleTime (* 3 60 60)))
    {:datasource datasource}))

(def c3p0 (delay (spec->pool spec)))

(defroutes
  ring-handler
  (GET "/" [] (read-resource! "index.html"))
  (GET "/test" []
    (jdbc/query @c3p0 ["SELECT * FROM Tags"]))
  (GET "/:path{.*\\.js}" [path] (println path) (read-resource! path))
  (route/not-found "Page not found!"))

(defn go! []
  (run-jetty ring-handler {:port 8080}))

(defn -main [] (go!))