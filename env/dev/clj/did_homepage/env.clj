(ns did-homepage.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [did-homepage.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[did-homepage started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[did-homepage has shut down successfully]=-"))
   :middleware wrap-dev})
