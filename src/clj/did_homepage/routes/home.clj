(ns did-homepage.routes.home
  (:require
    [did-homepage.layout :as layout]
    [clojure.java.io :as io]
    [did_homepage.pages.home :refer [render-home]]
    [did_homepage.pages.firebase :refer [render-fire]]
    [did-homepage.middleware :as middleware]
    [ring.util.http-response :as response]))


(defn home-page [request]
  (render-home request ))

(defn fire-page [request]
  (render-fire request ))

(defn home-routes []
  [""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/" {:get home-page}]
   ["/fire" {:get fire-page}]])

