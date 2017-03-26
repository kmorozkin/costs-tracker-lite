(defproject costs-tracker-lite "0.1.0-SNAPSHOT"
  :description "Conveinent costs tracker"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring "1.6.0-RC1"]
                 [compojure "1.5.2"]]
  :source-paths ["src/main/clojure" "src/main/html" "src/main/clojurescript" "src/main/css"]
  :test-paths   ["src/test/clojure" "src/test/html" "src/test/clojurescript" "src/test/css"]
  :main costs-tracker-lite.core
  :target-path "target/%s")