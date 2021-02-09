(ns crudik2.patients.events
  (:require [re-frame.core :refer [reg-event-fx]]))


(reg-event-fx
 :upsert-patient
 (fn [{:keys [db]} [_ {:keys [id name surname birthdate insurance-no]}]]
   (let [patient-id (or id (keyword (str "recipe-" (random-uuid))))]
     {:db (update-in db [:patients id] merge {:id patient-id
                                              :name name
                                              :surname surname
                                              :birthdate birthdate
                                              :insurance-no insurance-no})
      :dispatch [:close-modal]}) ))

(reg-event-fx
 :delete-patient
 (fn [{:keys [db]} [_ patient-id]]
   {:db (update-in db [:patients] dissoc patient-id)
    :dispatch [:close-modal]}))
