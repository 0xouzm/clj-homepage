(ns did_homepage.pages.firebase
  (:require [ring.util.http-response :refer [content-type ok]]
            [hiccup.core :refer [html]]))



(defn render-fire
  "renders the HTML template located relative to resources/html"
  [request & [params]]
  (content-type
    (ok (html [:html {:lang "en"}
               [:head
                [:meta {:charset "UTF-8"}]
                [:title "Sample FirebaseUI App"]
                [:script {:src "https://www.gstatic.com/firebasejs/6.1.1/firebase-app.js"}]
                [:script {:src "https://www.gstatic.com/firebasejs/6.1.1/firebase-auth.js"}]
                [:script {:src "https://www.gstatic.com/firebasejs/6.1.1/firebase-database.js"}]
                [:script {:src "https://www.gstatic.com/firebasejs/7.0.0/firebase-analytics.js"}]
                [:script "// Your web app's Firebase configuration
      var firebaseConfig = {
        apiKey: \"AIzaSyDoCxerTZe7G9PotE1KP6RAdwWGNku_Qxg\",
        authDomain: \"did-firebase.firebaseapp.com\",
        databaseURL: \"https://did-firebase.firebaseio.com\",
        projectId: \"did-firebase\",
        storageBucket: \"\",
        messagingSenderId: \"328744639787\",
        appId: \"1:328744639787:web:8a04dfcf71b34939b8796e\",
        measurementId: \"G-CCE9WFLGBN\"
      };
      // Initialize Firebase
      firebase.initializeApp(firebaseConfig);
      console.log(firebase.auth)
<!--      firebase.analytics();-->"]
                [:script {:src "https://cdn.firebase.com/libs/firebaseui/4.2.0/firebaseui.js"}]
                [:link {:type "text/css" :rel "stylesheet" :href "https://cdn.firebase.com/libs/firebaseui/4.2.0/firebaseui.css"}]
                [:script {:type "text/javascript"} "// FirebaseUI config.
      var uiConfig = {
        signInSuccessUrl: \"cpchain.io\",
        signInOptions: [
          // Leave the lines as is for the providers you want to offer your users.
          firebase.auth.GoogleAuthProvider.PROVIDER_ID,
          firebase.auth.EmailAuthProvider.PROVIDER_ID
        ],
        // tosUrl and privacyPolicyUrl accept either url string or a callback
        // function.
        // Terms of service url/callback.
        tosUrl: \"<your-tos-url>\",
        // Privacy policy url/callback.
        privacyPolicyUrl: function() {
          window.location.assign(\"<your-privacy-policy-url>\");
        }
      };

      // Initialize the FirebaseUI Widget using Firebase.
      var ui = new firebaseui.auth.AuthUI(firebase.auth());
      // The start method will wait until the DOM is loaded.
      ui.start(\"#firebaseui-auth-container\", uiConfig);"]]
               [:body
                [:h1 "Welcome to My Awesome App"]
                [:div#firebaseui-auth-container]]]))
    "text/html; charset=utf-8"))


