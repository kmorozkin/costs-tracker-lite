(ns costs-tracker-lite.db
  (:import (com.mchange.v2.c3p0 ComboPooledDataSource)))

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
