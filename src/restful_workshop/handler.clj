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
           (POST "/consumer" request (let [ssn (or (get-in request [:params :ssn])
                                                   (get-in request [:body :ssn])
                                                   "00000")]
                                       {:status 200
                                        :body   {:name ssn
                                                 :desc (str "ssn received " ssn)}}))
           (route/not-found "Not Found"))

(def app
  (-> app-routes
      (middleware/wrap-json-body {:keywords? true})
      (middleware/wrap-json-response)
      (wrap-defaults api-defaults)
      ))


(defn start []
  (web/run app))