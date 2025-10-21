(ns url-shortener.controllers.handler
  (:gen-class)
  (:require
   [url-shortener.services.short-url :refer [short-url-service redirect-url-service]]
   [clojure.string :as string]
   [url-shortener.helpers.helper :as helpers]))


(defn redirect-controller [req] (let [short-id (:id (:params req)) url (redirect-url-service short-id)]
                                   (try
                                     (println url "url")
                                     (if (not= url nil)
                                       (helpers/redirect (java.net.URLDecoder/decode url))
                                       (helpers/response 404 "Not found" nil))
                                     (catch Exception e (println e) (helpers/internal-server-error-exception)))))

(defn short-controller [req] (let [body (:body req) url (string/replace-first (clojure.core/slurp body) #"url=" "")]
                            (try
                              (if (helpers/url-valid? url)
                                (let [short-id (short-url-service url)]
                                  (println short-id)
                                  (helpers/redirect (str "/?short-id=" short-id)))
                                (helpers/response 422 "URL invalid" nil))
                              (catch Exception e (println e) (helpers/internal-server-error-exception)))))
