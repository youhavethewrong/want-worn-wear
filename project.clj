(defproject want-worn-wear "0.1.0-SNAPSHOT"
  :description "Watch for interesting things to show up on worn wear."
  :url "https://github.com/youhavethewrong/inth"
  :license {:name "Apache License, Version 2.0"
            :url "https://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.9.0-beta1"]
                 [org.clojure/data.json "0.2.6"]
                 [clj-http "3.0.1"]
                 [vincit/venia "0.2.3"]]
  :main ^:skip-aot want-worn-wear.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
