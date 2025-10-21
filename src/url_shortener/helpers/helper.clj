(ns url-shortener.helpers.helper
  (:require
    [clojure.data.json :as json]))

(defn url-valid? [url]
  (let [url-decoded (java.net.URLDecoder/decode url)]
    (not= (re-matches
           #"https?:\/\/(www\.)?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b([-a-zA-Z0-9()@:%_\+.~#?&//=]*)"
           url-decoded) nil)))

(defn response [status message data]
  {:headers {"Content-Type" "application/json"}
   :status status
   :body (json/write-str
          {:message message
           :data data})})

(defn redirect [url]
  {:status 302
   :headers {"Location" url}})

(defn internal-server-error-exception []
  (response 500 "Internal Server Error" nil))

(defn gen-id []
  (let [chars (map char (concat (range 48 57) (range 65 90) (range 97 122)))]
    (apply str (repeatedly 10 #(rand-nth chars)))))
