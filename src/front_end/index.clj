(ns front-end.index
  (:require [hiccup2.core :refer [ html ]]
            [clojure.string :as string]
            [dotenv :refer [env]])
  (:gen-class))

(defn copy-url [url](str "navigator.clipboard.writeText('"url"');" "alert('URL copiada! "url"')"))

(defn index [req]
  (let [q (:query-string req) short-id (if (not= q nil) (string/replace-first q #"short-id=" "") "")]
  (str
   (html
    [:html
     [:head
      [:title "Encurtador de URL"]
      [:script {:src "https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"}]]
     [:body {:class "w-full min-h-screen flex justify-center items-center bg-[#09090B] text-white bg-cover bg-center bg-[url('https://i.pinimg.com/originals/12/b2/3a/12b23a7752e8a7a4464c1ff5e596237f.gif')]"}
      [:div { :class "absolute min-h-screen w-full bg-black/40 backdrop-blur-sm" }]
      [:form {:method "POST" :action "/api/short-url" :class "flex flex-col items-center w-full max-w-xl z-10"}
       [:div {:class "flex gap-4 items-center"}
        [:img {:class "mb-4" :src "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5d/Clojure_logo.svg/1200px-Clojure_logo.svg.png" :width 100}]
        [:h2 {:class "text-5xl font-bold relative left-2"} "+"]
        [:img {:src "https://www.datomic.com/images/datomic-logo-160x130.png" :width 130}]]
       [:h2 {:class "text-3xl text-center font-bold mb-4 text-[#92DD45]"} "Encurtador de URL"]
       (if (string/blank? short-id)
         [:div [:input {:type "URL" :placeholder "Insira uma URL" :class "w-full mb-4 border border-white/20 p-2 rounded-md text-white/80" :name "url"}]
          [:button {:type "submit" :class "w-full py-2 px-8 bg-[#5782D9] rounded-md font-semibold"} "Encurtar URL"]]

         [:div [:input {:type "URL" :disabled true :value (str (env "FRONTEND_URL") short-id) :class "w-full mb-4 border border-white/20 p-2 rounded-md text-white/80" :name "url"}]
          [:button {:onclick (copy-url (str (env "FRONTEND_URL") short-id)) :type "button" :class "w-full py-2 px-8 bg-[#5782D9] rounded-md font-semibold"} "Copiar URL"]
          [:a {:href "/" :class "text-blue-400 text-center underline"} [:p {:class "mt-4  font-semibold"} "< Encurtar outra URL"]]])]]]))))
