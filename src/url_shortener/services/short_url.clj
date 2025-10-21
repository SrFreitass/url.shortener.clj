(ns url-shortener.services.short-url
  (:gen-class)
  (:require [url-shortener.db.db :as db]
            [url-shortener.helpers.helper :as helpers]))

(defn gen-short-id [chars]
  (apply str (repeatedly 10 #(rand-nth chars))))

(defn short-url-service [url]
    (loop [short-id (helpers/gen-id)]
          (if (= (db/get-url short-id) nil)
            (do (db/create-url short-id url) short-id)
            (recur (gen-short-id chars)))))

(defn redirect-url-service [short-id]
  (let [url (db/get-url short-id)]
    (if (not= url nil) url nil)))
