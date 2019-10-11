(ns did-homepage.events
  (:require
    [re-frame.core :as rf]))

;;dispatchers

(rf/reg-event-db
  :navigate
  (fn [db [_ route]]
    (assoc db :route route)))


(rf/reg-event-db
  :common/set-error
  (fn [db [_ error]]
    (assoc db :common/error error)))


;;subscriptions

(rf/reg-sub
  :route
  (fn [db _]
    (-> db :route)))

(rf/reg-sub
  :page
  :<- [:route]
  (fn [route _]
    (-> route :data :name)))


(rf/reg-sub
  :common/error
  (fn [db _]
    (:common/error db)))
