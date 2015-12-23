(ns clj-api.yesql
  (:require
    [clj-api.utils :refer [random-id]]
    [clj-api.db-config :refer [db-spec]]
    [yesql.core :refer [defqueries defquery]]
    [clojure.set :as set]))

(defn- rename-keys [x]
  (set/rename-keys x {:candidateid :candidateId :offerid :offerId}))

; all queries in one file
#_(defqueries "clj_api/yesql/docusign_contexts.sql"
            {:connection db-spec})

; or each query in a separate file
(defquery all-docusign-contexts
          "clj_api/yesql/all-docusign-contexts.sql"
          {:connection db-spec})

(defquery get-docusign-context-by-id
          "clj_api/yesql/get-docusign-context-by-id.sql"
          {:connection db-spec})

; <! means this is an insert statement
(defquery insert-docusign-context<!
          "clj_api/yesql/insert-docusign-context.sql"
          {:connection db-spec})

(defn list-docusign-contexts []
  (all-docusign-contexts {}
                         {:row-fn rename-keys}))

(defn get-docusign-context [id]
  (get-docusign-context-by-id {:id id}
                              {:row-fn rename-keys
                               :result-set-fn first}))

(defn create-docusign-context [docusign-context]
  (let [docusign-context (assoc docusign-context :id (random-id))]
    (insert-docusign-context<! docusign-context)
    docusign-context))

