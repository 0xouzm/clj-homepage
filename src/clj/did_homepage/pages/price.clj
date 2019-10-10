(ns did_homepage.pages.price
  (:require [ring.util.http-response :refer [content-type ok]]
            [did_homepage.pages.home :refer [navbar]]
            [hiccup.core :refer [html]]))



(defn body []
  [:html.has-navbar-fixed-top
   [:head
    [:meta {:charset "UTF-8"}]
    [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
    [:title "did-homepage"]]
   [:body
    (navbar)
    [:section.hero.is-medium.is-bold
     [:div.hero-body
      [:div.container
       [:h1.title. "Great teams trust & use CircleCI"]
       [:h2.subtitle "Fast. Functional. Flexible. Join thousands of other developer teams in trusting CircleCI for your testing and CI/CD needs."]]]]]
   [:link {:href "/css/screen.css" :rel "stylesheet" :type "text/css"}]])
;[:script {:src "/js/app.js" :type "text/javascript"}]]])



(defn render-price
  [request & [params]]
  (content-type
    (ok (html (body)))
    "text/html; charset=utf-8"))