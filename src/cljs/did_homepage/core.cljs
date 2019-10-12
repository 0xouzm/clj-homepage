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

(defn price-item [is-featured]
  [:div.column.price-calculator-preview.is-marginless.is-paddingless.has-text-black-bis {:class (when (= "featured" is-featured) "featured")}
   [:div.calculator-preview-heading
    [:h4.is-uppercase "Seed"]
    [:h5.is-size-3.is-marginless "$" [:strong "39"] "/mo"]]
   [:div.calculator-preview-item
    [:span.value "2x"] " concurrency"]
   [:div.calculator-preview-item "Recommended for teams building" [:span.value " 1-5 builds/day"]]
   [:div.calculator-preview-item
    [:span.value "500"] " max minutes/month"]
   [:div.calculator-preview-item "Community support"]
   [:div.calculator-preview-item "Recommended for " [:span.value "1-2"] "  team members"]
   [:div.pricing-action
    [:a.is-success.button.is-medium.price-btn {:href "/signup/" :role "button" :data-optimizely "signup_button_clicked" :data-analytics-action "signup-clicked" :data-analytics-location "osx-pricing-tiles-seed"}
     [:span.is-size-6.has-text-weight-bold "Sign Up"]]] (when (= "featured" is-featured) [:div.feature-flag "Free trial starts here"])])

(defn osx-page []
  [:div.container>div.content.osx-page
   [:div.is-size-4.has-text-centered.has-text-weight-semibold "Start with a Free 2-week macOS trial"]
   [:div.columns
    [price-item]
    [price-item]
    [price-item "featured"]
    [price-item]]

   [:div.section.has-text-centered "Interested in an " [:a.link "open-source discount"] "?"]])

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
  ; need to fix price initial page error!!
  (rf/dispatch-sync [:navigate (reitit/match-by-name router :osx)])
  (ajax/load-interceptors!)
  (hook-browser-navigation!)
  (mount-components))
