(ns url-shortener.core
  (:gen-class)
  (:require [org.httpkit.server :as server]
            [url-shortener.routes.routes :refer [app-routes]]
            [dotenv :refer [env]]))

(defn -main
  "Ponto de entrada da aplicação"
  []
  (let [port (Integer/parseInt (env "PORT"))]
    (server/run-server #'app-routes {:port port})
    (println (str "Rodando o servidor em http://127.0.0.1:" port "/"))))
