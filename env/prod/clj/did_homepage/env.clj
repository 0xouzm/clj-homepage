(ns did-homepage.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[did-homepage started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[did-homepage has shut down successfully]=-"))
   :middleware identity})
