(ns clj-api.utils
  (:import (java.util UUID)))

(defn random-id []
  (-> (UUID/randomUUID)
      (.toString)
      (.replaceAll "-" "")))
