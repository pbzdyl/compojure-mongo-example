(ns clj-api.handler-minimal
  (:require
    [clj-api.mongo :refer [get-all-people get-person save-person]]
    [compojure.core :refer :all]
    [compojure.route :as route]
    [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
    [ring.util.response :refer [response]]))

(defroutes app-routes
  (GET "/" [] "Welcome")
  (context "/people" []
    (GET "/" [] (response (get-all-people)))
    (GET "/:id" [id] (response (get-person id)))
    (POST "/" {person :body} (response (save-person person)))
    (route/not-found "Page not found")))

(def app
  (-> app-routes
      (wrap-json-response)
      (wrap-json-body)))
