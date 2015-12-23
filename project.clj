(defproject clj-api "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [clj-time "0.9.0"] ; required due to bug in lein-ring
                 [metosin/compojure-api "0.22.0"]
                 [com.novemberain/monger "3.0.1"]
                 [korma "0.4.0"]
                 [yesql "0.5.1"]
                 [org.postgresql/postgresql "9.4-1206-jdbc42"]
                 [com.h2database/h2 "1.4.190"]
                 [org.hsqldb/hsqldb "2.3.3"]
                 [ring/ring-json "0.4.0"]]
  :ring {:handler clj-api.handler/app}
  :uberjar-name "server.jar"
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [cheshire "5.3.1"]
                                  [ring-mock "0.1.5"]]
                   :plugins [[lein-ring "0.9.6"]]}})
