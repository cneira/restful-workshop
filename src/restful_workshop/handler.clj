
(ns restful-workshop.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :refer [resource-response response]]
            [ring.middleware.json :as middleware]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults api-defaults]]
            [immutant.web :as web]
            [restful-workshop.dbaccess :as db]
            )
  )

(defn return-params [request]
  (if (empty? (request :body))
    (request :params)
    (request :body)))


; We are expecting this json,
; { “ssn”: <a ssn for the individual> ,
;   “first_name”: <first_name>,
;   “last_name”: <last_name>,
;   “timestamp”: <from when to check records>,
;   “type_of_crime”: <economic/murder/other>
;
;  }


(defn generate-response [request]
  (let [{:keys [ssn first_name last_name timestamp type_of_crime]} (return-params request)]
    {:x ssn :y first_name :z type_of_crime}))

(defn return-criminal [request]
  (db/get-criminal (return-params request))
  )


(defroutes app-routes

           (POST "/profiles" request {:status 200
                                      :body   (return-criminal request)
                                      })

           (route/not-found "Not Found"))


(def app
  (-> app-routes
      ; {:keywords? true} when the body is parsed makes the keys return as keywords
      ; and not as strings
      (middleware/wrap-json-body {:keywords? true})
      (middleware/wrap-json-response)
      (wrap-defaults api-defaults)))


(defn start []
  (web/run app))