(ns want-worn-wear.db
  (:require [clojure.java.jdbc :as jdbc])
  (:import org.sqlite.SQLiteException))

(defn define-schema
  [db]
  (jdbc/execute!
   db
   ["create table wear (id varchar(255) primary key not null, title varchar(255), url varchar(255), price numeric, last_modified datetime default CURRENT_TIMESTAMP)"]))

(defn insert-worn-wear
  [db data]
  (try
    (jdbc/insert! db :wear data)
    (catch SQLiteException ex
      (if (.contains (.getMessage ex) "UNIQUE constraint failed: wear.id")
        (jdbc/execute! db ["update wear set last_modified = CURRENT_TIMESTAMP where id = ?" (:id data)])
        (println (.getMessage ex))))))

(defn get-worn-wear
  [db]
  (jdbc/query db ["select * from wear"]))

(defn migrate-schema
  "SQLite doesn't allow the addition of a column with CURRENT_TIMESTAMP as the default value, so we'll have to select out the
   contents, drop the table, create the new structure, and then re-insert the contents."
  [db]
  (let [current-data (get-worn-wear db)]
    (println "Got" (count current-data) "rows.")
    (jdbc/execute! db ["drop table wear"])
    (define-schema db)
    (doseq [datum current-data]
      (insert-worn-wear db datum))
    (get-worn-wear db)))
