(ns want-worn-wear.core
  (:require [clojure.data.json :as json]
            [clj-http.client :as client]
            [clojure.java.io :as io]
            [venia.core :as v])
  (:gen-class))

(def search-url "https://reware-production.yerdle.io/v4/graphql")
(def useragent "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36")

(def ww-query
  (v/graphql-query
   {:venia/queries [[:partner {:uuid "7d32ad83-330e-4ccc-ba03-3bb32ac113ac"}
                     [[:categories {:slug "mens"}
                       [:slug :title [:inventoryItemsForSale {:limit 50 :size "M"}
                                      [:availableSizes :color :imageUrls :parentSKU
                                       :price :title :priceRange]]]]]]]}))


(def standard-params
  {:body (json/write-str {:query ww-query})
   :client-params {"http.useragent" useragent}
   :headers {"Accept-Language" "en-US,en;q=0.5"
             "Content-Type" "application/json"
             "Referer" "https://wornwear.patagonia.com/shop/mens"
             "origin" "https://wornwear.patagonia.com"
             "DNT" "1"
             "Connection" "keep-alive"}})

(defn post-search
  [url]
  (:body (client/post url standard-params)))

(defn save-results
  [f]
  (spit f (post-search search-url)))

(defn -main
  [& args]
  (println "Hello, World!"))
