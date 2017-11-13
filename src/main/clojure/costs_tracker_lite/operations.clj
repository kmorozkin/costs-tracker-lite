(ns costs-tracker-lite.operations
  (:require [clojure.string :as s]
            [clojure.java.jdbc :as jdbc])
  (:import (javax.sql DataSource)
           (java.time LocalDateTime LocalDate)))

(defn- prepare
  "Transforms objects' fields into table-valid data types"
  [object]
  (update object :date #(LocalDate/parse %)))

(defn- insert-id
  "Inserts generated id into the object. Id is obtained from insert-result"
  [object insert-result]
  (if-let [result-map (first insert-result)]
    (merge object result-map)))

(defn persist-spending!
  "Persists the spendings table row. Returns the object with appended id if success. Note:
  the object is assumed to be insert-ready"
  [^DataSource connection object]
  (let [spending-keys [:author :item :date :price]]
    (insert-id object (jdbc/insert! connection "spendings" (select-keys object spending-keys)))))

(defn persist-spending-tags!
  "Persists spending tags if any. Note: the object is assumed to be insert-ready"
  [^DataSource connection object]
  (if-let [tags (get object :tags)]
    (jdbc/insert-multi!
      connection
      "spendingtags"
      ["spendingid" "tagid"]
      (map #(vector (:id object) %) tags)))
  object)

(defn new-tag!
  "Writes the new tag into the database. Returns its id on success or nil on failure."
  [^DataSource connection tag]
  (let [statement (str "INSERT INTO tags(name) values ('" tag "') ON CONFLICT DO NOTHING RETURNING id")]
    (:id (first (jdbc/query connection [statement])))))

(defn persist!
  "Persists the entire object. Prepares, writes the spending and its tags.
  Returns the persisted object."
  [^DataSource connection object]
  (->>
    object
    prepare
    (persist-spending! connection)
    (persist-spending-tags! connection)))