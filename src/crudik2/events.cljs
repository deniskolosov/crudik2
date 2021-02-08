(ns crudik2.events
  (:require [re-frame.core :refer [reg-event-db]]))


(reg-event-db
 :open-modal
 (fn [db [_ modal-name]]
   (assoc-in db [:active-modal] modal-name)))


(reg-event-db
 :close-modal
 (fn [db _]
   (assoc-in db [:active-modal] nil)))
