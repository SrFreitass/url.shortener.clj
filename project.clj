(defproject url-shortener "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :main url-shortener.core
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [http-kit "2.8.1"]
                 [org.clojure/data.json "2.5.1"]
                 [compojure "1.7.2"]
                 [ring/ring-json "0.5.1"]
                 [com.taoensso/carmine "3.4.1"]
                 [hiccup "2.0.0"]
                 [com.thheller/shadow-css "0.6.2"]
                 [io.github.tonsky/clj-reload "1.0.0"]
                 [org.clojure/clojurescript "1.12.42"]
                 [com.datomic/local "1.0.291"]
                 [lynxeyes/dotenv "1.1.0"]]
  :repl-options {:init-ns url-shortener.core})
