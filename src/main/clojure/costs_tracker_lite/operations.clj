(ns costs-tracker-lite.operations
  (:require [clojure.string :as s]
            [clojure.java.jdbc :as jdbc])
  (:import (javax.sql DataSource)
           (java.time LocalDateTime LocalDate)))

(defn persist!
  [object ^DataSource connection]
  (jdbc/insert!
    connection
    "spendings"
    (update
      (select-keys object [:author :date :item :price])
      :date
      #(LocalDate/parse %))))

(defn new-tag
  [tag ^DataSource connection]
  (let [statement (str "INSERT INTO tags(name) values ('" tag "') ON CONFLICT DO NOTHING")]
    (some? (first (jdbc/execute! connection [statement])))))