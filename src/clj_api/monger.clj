(ns clj-api.monger
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [monger.json])
  (:import [org.bson.types ObjectId]))

(def conn (mg/connect {:host "localhost"}))

(def db (mg/get-db conn "test"))

(def docusign-contexts-coll "docusign-contexts")

(defn objectId->str [e]
  (if-let [objectId (:_id e)]
    (-> e (dissoc :_id) (assoc :id (str objectId)))
    e))

(def objectIds->str (partial map objectId->str))

(defn list-docusign-contexts []
  (-> (mc/find-maps db docusign-contexts-coll)
      objectIds->str))

(defn get-docusign-context [id]
  (-> (mc/find-map-by-id db docusign-contexts-coll (ObjectId. id))
      objectId->str))

(defn create-docusign-context [docusign-context]
  (-> (mc/insert-and-return db docusign-contexts-coll (assoc docusign-context :_id (ObjectId.)))
      objectId->str))

