(ns clj-api.db-config
  (:require [korma.db :as kdb]
            [korma.core :as kc]
            [clojure.java.jdbc :as jdbc]))

; set one of
;   :h2
;   :hsql
;   :postgres
(def db-choice :postgres)

(def db-specs
  {:h2       (kdb/h2 {:db "mem:test;DB_CLOSE_DELAY=-1"})
   :hsql     {:classname   "org.hsqldb.jdbcDriver"
              :subprotocol "hsqldb"
              :subname     "mem:test"
              :make-pool?  true?}
   :postgres (kdb/postgres {:db "docusign-contexts"})})

(def db-spec (db-choice db-specs))

; init DB
(println "(Re)creating DB schema")
(jdbc/db-do-commands
  db-spec
  "DROP TABLE IF EXISTS DOCUSIGN_CONTEXTS"
  "CREATE TABLE DOCUSIGN_CONTEXTS(
   \"id\" varchar(64) PRIMARY KEY,
   \"candidateId\" varchar(64),
   \"offerId\" varchar(64))")
