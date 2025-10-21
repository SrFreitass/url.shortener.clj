(ns url-shortener.routes.routes
  (:gen-class)
  (:require [compojure.core :refer [GET POST defroutes]]
            [compojure.route :as route]
            [url-shortener.controllers.handler :refer [ short-controller redirect-controller]]
            [front-end.index]))

(defroutes app-routes
  (GET "/" [] front-end.index/index)
  (GET "/:id" [] redirect-controller)
  (POST "/api/short-url" [] short-controller)
  (route/not-found "Not found"))
