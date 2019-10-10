(ns did_homepage.pages.home
  (:require [ring.util.http-response :refer [content-type ok]]
            [hiccup.core :refer [html]]
            [ring.middleware.anti-forgery :refer [*anti-forgery-token*]]))



(defn render-home
  [request & [params]]
  (content-type
    (ok (html [:html
               [:head
                [:meta {:charset "UTF-8"}]
                [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
                [:title "Welcome to did-homepage"]]
               [:body
                [:div#app
                 [:section.section
                  [:div.container.is-fluid
                   [:div.content
                    [:h4.title "Welcome to did-homepage"]
                    [:p "If you're seeing this message, that means you haven't yet compiled your ClojureScript!"]
                    [:p "Please run" [:code "lein shadow watch app"] "to start the ClojureScript compiler and reload the page."]
                    [:h4 "For better ClojureScript development experience in Chrome follow these steps:"]
                    [:ul
                     [:li "Open DevTools"] [:li "Go to Settings (\"three dots\" icon in the upper right corner of DevTools > Menu > Settings F1 > General > Console)"] [:li "Check-in \"Enable custom formatters\""] [:li "Close DevTools"] [:li "Open DevTools"]]
                    [:p "See" [:a {:href "http://www.luminusweb.net/docs/clojurescript.md"} "ClojureScript"] "documentation for further details."]]]]]
                [:link {:href "/css/screen.css" :rel "stylesheet" :type "text/css"}]
                [:script {:type "text/javascript"} (str "var csrfToken = \" " *anti-forgery-token* ";\"")]
                [:script {:src "/js/app.js" :type "text/javascript"}]]]))
    "text/html; charset=utf-8"))