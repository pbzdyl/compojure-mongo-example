(ns clj-api.handler-minimal
  (:require
    [clj-api.monger :refer [list-docusign-contexts get-docusign-context create-docusign-context]]
    [compojure.core :refer :all]
    [compojure.route :as route]
    [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
    [ring.util.response :refer [response]]))

(defroutes app-routes
  (GET "/" [] "Welcome")
  (context "/docusign-contexts" []
    (GET "/" [] (response (list-docusign-contexts)))
    (GET "/:id" [id] (response (get-docusign-context id)))
    (POST "/" {docusign-context :body} (response (create-docusign-context docusign-context)))
    (route/not-found "Page not found")))

(def app
  (-> app-routes
      (wrap-json-response)
      (wrap-json-body)))
