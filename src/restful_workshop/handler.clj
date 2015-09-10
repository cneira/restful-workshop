(ns restful-workshop.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :refer [resource-response response]]
            [ring.middleware.json :as middleware]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults api-defaults]]
            [immutant.web :as web])
  )

(defroutes app-routes
           (GET "/" [] (response {:what "Hello World"}))
           (POST "/consumer" {body :body} (response {:a body}))
           (route/not-found "Not Found"))

(def app
  (-> app-routes
      (middleware/wrap-json-body)
      (middleware/wrap-json-response)
      (wrap-defaults api-defaults)
      ))


(defn start []
  (web/run app))