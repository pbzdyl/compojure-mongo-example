(ns clj-api.korma
  (:require
    [clj-api.utils :refer [random-id]]
    [clj-api.db-config :refer [db-spec]]
    [korma.db :as kdb]
    [korma.core :as kc]))

(kdb/defdb db db-spec)

(kc/defentity
  docusign-contexts
  (kc/pk :id)
  (kc/table "DOCUSIGN_CONTEXTS")
  (kc/database db)
  (kc/entity-fields :id :candidateId :offerId))

(defn list-docusign-contexts []
  (kc/select docusign-contexts))

(defn get-docusign-context [id]
  (first (kc/select
     docusign-contexts
     (kc/where {:id id}))))

(defn create-docusign-context [docusign-context]
  (let [docusign-context-with-id (assoc docusign-context :id (random-id))]
    (kc/insert docusign-contexts (kc/values docusign-context-with-id))
    docusign-context-with-id))

