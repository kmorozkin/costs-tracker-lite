(defproject costs-tracker-lite "0.1.0-SNAPSHOT"
  :description "Conveinent costs tracker"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.495"]
                 [ring "1.6.2"]
                 [compojure "1.6.0"]
                 [org.postgresql/postgresql "42.1.4"]
                 [org.clojure/java.jdbc "0.7.0"]
                 [com.mchange/c3p0 "0.9.5.2"]]
  :plugins [[lein-cljsbuild "1.1.5"]]
  :source-paths ["src/main/clojure" "src/main/html" "src/main/css" "src/main/sql"]
  :resource-paths ["src/main/resources" "target/public"]
  :test-paths ["src/test/clojure" "src/test/html" "src/test/css"]
  :main costs-tracker-lite.core
  :aot [costs-tracker-lite.core]

  :cljsbuild {:builds [{:source-paths ["src/main/clojurescript"]
                        :test-paths ["src/test/clojurescript"]
                        :compiler     {:output-to     "target/public/js/main.js"
                                       :main          "costs-tracker-lite.core"
                                       :optimizations :simple}}]}
  :hooks [leiningen.cljsbuild])