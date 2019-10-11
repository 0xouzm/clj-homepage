(ns did-homepage.core
  (:require
    [day8.re-frame.http-fx]
    [reagent.core :as r]
    [re-frame.core :as rf]
    [goog.events :as events]
    [goog.history.EventType :as HistoryEventType]
    [markdown.core :refer [md->html]]
    [did-homepage.ajax :as ajax]
    [did-homepage.events]
    [reitit.core :as reitit]
    [clojure.string :as string])
  (:import goog.History))

(defn tab-link [uri title page]
  [:li {:class (when (= page @(rf/subscribe [:page])) :is-active)}
   [:a {:href uri} title]])

(defn tabbar []
  [:div.tabs.is-centered
   [:ul
    [tab-link "#/build-linux" "Build on Linux" :linux]
    [tab-link "#/build-os-x" "Build on macOX" :osx]
    [tab-link "#/build-enterprise" "Self-hosted" :enterprise]]])

(defn linux-page []
  [:section.section>div.container>div.content
   "1"])
(defn osx-page []
  [:section.section>div.container>div.content
   "2"])
(defn enterprise-page []
  [:section.section>div.container>div.content
   "3"])

(def pages
  {
   :linux      #'linux-page
   :osx        #'osx-page
   :enterprise #'enterprise-page})


(defn page []
  [:div
   [tabbar]
   [(pages @(rf/subscribe [:page]))]])

;; -------------------------
;; Routes

(def router
  (reitit/router
    [
     ["/build-linux" :linux]
     ["/build-os-x" :osx]
     ["/build-enterprise" :enterprise]]))


;; -------------------------
;; History
;; must be called after routes have been defined
(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
      HistoryEventType/NAVIGATE
      (fn [event]
        (let [uri (or (not-empty (string/replace (.-token event) #"^.*#" "")) "/")]
          (rf/dispatch
            [:navigate (reitit/match-by-path router uri)]))))
    (.setEnabled true)))

;; -------------------------
;; Initialize app
(defn ^:dev/after-load mount-components []
  (rf/clear-subscription-cache!)
  (r/render [#'page] (.getElementById js/document "app")))


(defn init! []
  (rf/dispatch-sync [:navigate (reitit/match-by-name router :linux)])
  (ajax/load-interceptors!)
  (hook-browser-navigation!)
  (mount-components))
