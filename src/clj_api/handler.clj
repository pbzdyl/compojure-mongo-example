(ns clj-api.handler
  (:require
    ; change clj-api.* to one of
    ;   clj-api.monger
    ;   clj-api.korma
    ;   clj-api.yesql
    ; for different persistence implementations
    [clj-api.yesql :refer [list-docusign-contexts get-docusign-context create-docusign-context]]
    [compojure.api.sweet :refer :all]
    [ring.util.http-response :refer :all]
    [schema.core :as s]))


(s/defschema Message {:message String})

(s/defschema NewDocusignContext {:candidateId String :offerId String})

(s/defschema DocusignContext (assoc NewDocusignContext :id String))

(defapi app
  (swagger-ui)
  (swagger-docs
    {:info {:title "Clj-api"
            :description "Compojure Api example"}
     :tags [{:name "hello", :description "says hello in Finnish"}]})

  (context* "/hello" []
    :tags ["hello"]
    (GET* "/" []
      :return Message
      :query-params [name :- String]
      :summary "say hello"
      (ok {:message (str "Terve, " name)})))

  (context* "/docusign-contexts" []
    :tags ["Docusign Contexts"]

    (GET* "/" []
      :return [DocusignContext]
      :summary "List all Docusign Contexts"
      (ok (list-docusign-contexts)))

    (GET* "/:id" [id]
      :return DocusignContext
      :summary "Return Docusign Context with provided id"
      (if-let [docusign-context (get-docusign-context id)]
        (ok docusign-context)
        (not-found (str "No Docusign Context with id " id))))

    (POST* "/" []
       :body [docusign-context NewDocusignContext]
       :return DocusignContext
       :summary "Create a Docusign Context record"
       (ok (create-docusign-context docusign-context)))))
