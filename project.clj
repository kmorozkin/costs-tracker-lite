(defproject costs-tracker-lite "0.1.0-SNAPSHOT"
  :description "Conveinent costs tracker"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.495"]
                 [ring "1.6.0-RC1"]
                 [compojure "1.5.2"]]
  :plugins [[lein-cljsbuild "1.1.5"]]
  :source-paths ["src/main/clojure" "src/main/html" "src/main/css"]
  :test-paths ["src/test/clojure" "src/test/html" "src/test/css"]
  :main costs-tracker-lite.core
  :target-path "target/%s"

  :cljsbuild {:builds [{:source-paths ["src/main/clojurescript"]
                        :test-paths ["src/test/clojurescript"]
                        :compiler     {:output-to     "main.js"
                                       :main          "costs-tracker-lite.core"
                                       :optimizations :simple}
                        :jar true}]}
  :hooks [leiningen.cljsbuild])