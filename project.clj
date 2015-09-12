(defproject restful-workshop "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [ring/ring-defaults "0.1.4"]
                 [ring/ring-core "1.4.0"]
                 [ring/ring-json "0.4.0"]
                 [org.immutant/web "2.1.0"]
                 [com.h2database/h2 "1.3.170"]
                 [org.clojure/java.jdbc "0.3.6"]
                 [java-jdbc/dsl "0.1.0"]
                 ]
  :plugins [[lein-ring "0.8.13"] [lein-immutant "2.0.0"]]
  :ring {:handler restful-workshop.handler/app}
  :main restful-workshop.handler/start
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
