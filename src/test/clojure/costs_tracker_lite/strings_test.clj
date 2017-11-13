(ns costs-tracker-lite.strings-test
  (:require [clojure.test :refer :all]
            [costs-tracker-lite.strings :as strings]))

(deftest
  test-format
  (is (= "This is a test string that is fine"
       (strings/format "This {0} a {1} string that {0} fine" "is" "test"))))