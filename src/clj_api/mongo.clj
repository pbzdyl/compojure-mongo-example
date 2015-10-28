(ns clj-api.mongo
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [monger.json])
  (:import [org.bson.types ObjectId]))

(def conn (mg/connect {:host "localhost"}))

(def db (mg/get-db conn "test"))

(def people-coll "people")

(defn objectId->str [e]
  (if-let [objectId (:_id e)]
    (-> e (dissoc :_id) (assoc :id (str objectId)))
    e))

(def objectIds->str (partial map objectId->str))

(defn get-all-people []
  (-> (mc/find-maps db people-coll)
      objectIds->str))

(defn get-person [id]
  (-> (mc/find-map-by-id db people-coll (ObjectId. id))
      objectId->str))

(defn save-person [person]
  (-> (mc/insert-and-return db people-coll (assoc person :_id (ObjectId.)))
      objectId->str))

