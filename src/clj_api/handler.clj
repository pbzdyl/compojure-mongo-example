(ns clj-api.handler
  (:require
    [clj-api.mongo :refer [get-all-people get-person save-person]]
    [compojure.api.sweet :refer :all]
    [ring.util.http-response :refer :all]
    [schema.core :as s]))


(s/defschema Message {:message String})

(s/defschema NewPerson {:name String})

(s/defschema Person (assoc NewPerson :id String))

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

  (context* "/people" []
    :tags ["People"]

    (GET* "/" []
      :return [Person]
      :summary "List all people"
      (ok (get-all-people)))

    (GET* "/:id" [id]
      :return Person
      :summary "Return person with provided id"
      (if-let [person (get-person id)]
        (ok person)
        (not-found (str "No person with id " id))))

    (POST* "/" []
       :body [person NewPerson]
       :return Person
       :summary "Create a person record"
       (ok (save-person person)))))
