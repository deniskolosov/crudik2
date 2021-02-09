(ns crudik2.events
  (:require [re-frame.core :refer [reg-event-db reg-event-fx]]))


(reg-event-db
 :open-modal
 (fn [db [_ modal-name]]
   (assoc-in db [:active-modal] modal-name)))

(reg-event-db
 :set-active-patient
 (fn [db [_ patient-id]]
   (assoc-in db [:active-patient] patient-id)))


(reg-event-db
 :unset-active-patient
 (fn [db [_ _]]
   (assoc-in db [:active-patient] nil)))

(reg-event-fx
 :close-modal
 (fn [{:keys [db]} [_ _]]
   {:db (assoc-in db [:active-modal] nil)
    :dispatch [:unset-active-patient]}))
