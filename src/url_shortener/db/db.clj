(ns url-shortener.db.db
  (:gen-class)
  (:require [datomic.client.api :as d]
            [url-shortener.db.db :as db]))

(def client (d/client { :server-type :datomic-local :system "dev" :storage-dir "/app/.datomic/data" }))

(defn create-db [] (d/create-database client {:db-name "urls"}))

(create-db)

(def url-schema [{:db/ident :url/uri
                  :db/valueType :db.type/string
                  :db/cardinality :db.cardinality/one
                  :db/doc "The shortened URI"}
                 {:db/ident :url/short-id
                  :db/valueType :db.type/string
                  :db/cardinality :db.cardinality/one
                  :db/doc "The identifier for URI"}])

(def conn (d/connect client { :db-name "urls" }))

(defn create-schema []
  (d/transact conn {:tx-data url-schema}))

(create-schema)

(def query
  '[:find ?uri
    :in $ ?short-id
    :where [?e :url/uri ?uri]
           [?e :url/short-id ?short-id]])

(defn create-url [short-id uri]
  (d/transact conn { :tx-data [{:url/short-id short-id :url/uri uri }]}))

(defn get-url [short-id]
  (let [uri (get (d/q query (d/db conn) short-id) 0)]
    (if (not= uri nil) (uri 0) nil)))
