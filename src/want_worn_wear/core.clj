(ns want-worn-wear.core
  (:require [clojure.data.json :as json]
            [clj-http.client :as client])
  (:gen-class))

(def base-url "https://reware-production.yerdle.io/v4/graphql")
(def useragent "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36")

(def standard-params
  {:client-params {"http.useragent" useragent}
   :headers {"Accept-Language" "en-US,en;q=0.5"
             "Content-Type" "application/json"
             "Referer" "https://wornwear.patagonia.com/shop/mens"
             "origin" "https://wornwear.patagonia.com"
             "DNT" "1"
             "Connection" "keep-alive"}})

(defn post-search
  [url]
  (:body (client/post url standard-params)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
