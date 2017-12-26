(defproject want-worn-wear "0.1.0-SNAPSHOT"
  :description "Watch for interesting things to show up on worn wear."
  :license {:name "Apache License, Version 2.0"
            :url "https://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/java.jdbc "0.7.3"]
                 [org.clojure/data.json "0.2.6"]
                 [clj-http "3.0.1"]
                 [vincit/venia "0.2.3"]
                 [org.xerial/sqlite-jdbc "3.20.1"]]
  :repl-options {:timeout 180000
                 :host "0.0.0.0"}
  :main ^:skip-aot want-worn-wear.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
