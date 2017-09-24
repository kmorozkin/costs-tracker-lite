(ns costs-tracker-lite.core
  (:import com.mchange.v2.c3p0.ComboPooledDataSource)
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.adapter.jetty :refer [run-jetty]]
            [costs-tracker-lite.resources :refer [read-resource!]]
            [jdbc.pool.c3p0 :as c3p0]
            [clojure.java.jdbc :as jdbc])
  (:gen-class))

(def spec
    {:classname "org.postgresql.Driver"
     :subprotocol "postgresql"
     :subname "//localhost:5432/costs-tracker"
     :user "test"
     :password "test"})

(defroutes
  ring-handler
  (GET "/" [] (read-resource! "index.html"))
  (GET "/test" []
    (jdbc/query spec ["SELECT * FROM Tags"]))
  (GET "/:path{.*\\.js}" [path] (println path) (read-resource! path))
  (route/not-found "Page not found!"))

(defn go! []
  (run-jetty ring-handler {:port 8080}))

(defn -main [] (go!))