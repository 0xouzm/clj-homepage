(ns did_homepage.pages.home
  (:require [ring.util.http-response :refer [content-type ok]]
            [hiccup.core :refer [html]]))

(defn navbar []
  [:nav.navbar.is-fixed-top.is-transparent.is-black {:role "navigation" :aria-label "main navigation"}
   [:div.container
    [:div.navbar-brand
     [:a.navbar-item {:href "http://localhost:4000"}
      [:img {:src "https://d3r49iyjzglexf.cloudfront.net/logo-wordmark-26f8eaea9b0f6e13b90d3f4a8fd8fda31490f5af41daab98bbede45037682576.svg" :width "112" :height "28"}]]
     [:a.navbar-burger.burger {:role "button" :aria-label "menu" :aria-expanded "false" :data-target "navbarBasicExample"}
      [:span {:aria-hidden "true"}]
      [:span {:aria-hidden "true"}]
      [:span {:aria-hidden "true"}]]]
    [:div#navbarBasicExample.navbar-menu
     [:div.navbar-start
      [:a.navbar-item "Home"]
      [:a.navbar-item {:href "http://localhost:3000/price"} "Price"]
      [:a.navbar-item "Documentation"]
      [:div.navbar-item.has-dropdown.is-hoverable
       [:a.navbar-link "More"]
       [:div.navbar-dropdown
        [:a.navbar-item "About"]
        [:a.navbar-item "Jobs"]
        [:a.navbar-item "Contact"]
        [:hr.navbar-divider]
        [:a.navbar-item "Report an issue"]]]]
     [:div.navbar-end
      [:div.navbar-item
       [:div.buttons
        [:a.button.is-primary
         [:strong "Sign up"]]
        [:a.button.is-light "Log in"]]]]]]]
  )



(defn footer []
  [:footer.footer
   [:div.container
    [:div.columns.footer_list
     [:div.column.is-1]
     [:div.column [:p "Company"]
      [:ul
       (for [_ (range 5)]
         [:li [:a "About"]])]]


     [:div.column [:p "Products"]
      [:ul
       (for [_ (range 5)]
         [:li [:a "About"]])]]


     [:div.column [:p "Community"]
      [:ul
       (for [_ (range 9)]
         [:li [:a "About"]])]]


     [:div.column [:p "Solutions"]
      [:ul
       (for [_ (range 13)]
         [:li [:a "About"]])]]


     [:div.column [:p "Contact"]
      [:ul
       (for [_ (range 7)]
         [:li [:a "About"]])]]

     [:div.column.is-1]]
    [:hr.hr]
    [:div.columns

     [:div.column
      [:div.has-text-justified
       [:span.is-inline-block.logo_text "© 2019 DigitalOcean, LLC. All rights reserved."]]]


     [:div.column
      [:div.link.is-pulled-right]]]]])


(defn body []
  [:html.has-navbar-fixed-top
   [:head
    [:meta {:charset "UTF-8"}]
    [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
    [:title "did-homepage"]]
   [:body
    (navbar)
    [:section.hero.is-black.is-fullheight-with-navbar
     [:div.hero-body
      [:div.container
       [:div.columns.is-vcentered
        [:div.column.is-7
         [:h1.is-size-1.has-text-weight-light "We build CI/CD"] [:strong.is-size-1.has-text-white "so you can build"] [:br] [:strong.is-size-1.has-text-white "the next big thing."]]

        [:div.column.is-5
         [:img.hero-main-image {:src "https://d3r49iyjzglexf.cloudfront.net/illustrations/illu_homepage_studioiv-042b87b2f41fe6d0c5943b12f6cd1ff2a227dc84e3a7e76f2b8d57c05f1b1da4.png"}]]]

       [:div.horizontal-logos-flex-container
        [:img {:src "https://d3r49iyjzglexf.cloudfront.net/components/logos/logo-facebook-21c9b557a0256613f839560620126015f8823dbbce5d16c84b9c0b3e8d77b883.svg" :alt "facebook"}]
        [:img {:src "https://d3r49iyjzglexf.cloudfront.net/components/logos/logo-segment-dcc655d0f6899ccb7c830f8db971d2762b7707fe42fca029fed6426d31be28bd.svg" :alt "segment"}]
        [:img {:src "https://d3r49iyjzglexf.cloudfront.net/components/logos/logo-kickstarter-87ca874fc4d775bcd1ee09e9d9e545c239ff539b19ed83416923f7be8514ec30.svg" :alt "kickstarter"}]
        [:img {:src "https://d3r49iyjzglexf.cloudfront.net/components/logos/logo-percolate-58c6d30556bc6983f710297804ef0d4c25d2a0142061455f02b4ddce7f03944f.svg" :alt "percolate"}]
        [:img {:src "https://d3r49iyjzglexf.cloudfront.net/components/logos/logo-spotify-7607572ee7f5699b7299b34b4a20256085d7287b089f106a70bea5f8e2ceffed.svg" :alt "spotify"}]
        [:img {:src "https://d3r49iyjzglexf.cloudfront.net/components/logos/logo-gopro-dda4ae6f4f555c84d34925fc52712808c46144d4804f0299263695b149b6cfd8.svg" :alt "gopro"}]]]]]]

   [:section.hero.is-fullheight
    [:div.hero-body
     [:div.container
      [:h1.title "Large title"]
      [:h2.subtitle "Large subtitle"]]]]
   (footer)
   [:link {:href "/css/screen.css" :rel "stylesheet" :type "text/css"}]])
;[:script {:src "/js/app.js" :type "text/javascript"}]]])



(defn render-home
  [request & [params]]
  (content-type
    (ok (html (body)))
    "text/html; charset=utf-8"))