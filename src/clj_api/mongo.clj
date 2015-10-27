(ns clj-api.mongo
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [monger.json])
  (:import [org.bson.types ObjectId]))

(def conn (mg/connect {:host "localhost"}))

(def db (mg/get-db conn "test"))

(defn get-all-people []
  (mc/find-maps db "people"))

(defn get-person [id]
  (mc/find-one-as-map db "people" {:_id (ObjectId. id)}))

(defn save-person [person]
  (mc/insert-and-return db "people" (assoc person :_id (ObjectId.))))