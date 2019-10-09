(ns did-homepage.app
  (:require [did-homepage.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
