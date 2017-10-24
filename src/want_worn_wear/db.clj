(ns want-worn-wear.db
  (:require [clojure.java.jdbc :as jdbc])
  (:import [com.mchange.v2.c3p0 ComboPooledDataSource]
           [org.sqlite SQLiteException]))


(defn define-schema
  [db]
  (jdbc/execute!
   db
   ["create table wear (id varchar(255) primary key not null, title varchar(255), url varchar(255), price numeric)"]))

(defn insert-worn-wear
  [db data]
  (try
    (jdbc/insert! db :wear data)
    (catch SQLiteException ex
      (when-not (.contains (.getMessage ex) "UNIQUE constraint failed: wear.id")
        (println (.getMessage ex))))))

(defn get-worn-wear
  [db]
  (jdbc/query db ["select * from wear"]))
