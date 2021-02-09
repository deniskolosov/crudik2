(ns crudik2.patients.views.patients-page
  (:require [re-frame.core :as rf]
            [reagent.core :as r]
            ["@smooth-ui/core-sc" :refer [Box Button Row Col Typography]]
            [clojure.string :as str]
            [crudik2.components.form-group :refer [form-group]]
            [crudik2.components.modal :refer [modal]]))

(defn patients-page
  []
  (let [patients @(rf/subscribe [:patients])
        initial-values {:id "" :name "" :surname "" :birthdate "" :insurance-no ""}
        values (r/atom initial-values)
        open-modal (fn [{:keys [modal-name patient]}]
                     (rf/dispatch [:open-modal modal-name])
                     (reset! values patient))
        save (fn [event {:keys [id name surname birthdate insurance-no]}]
               (.preventDefault event)
               (rf/dispatch [:upsert-patient {:id id
                                              :name (str/trim name)
                                              :surname (str/trim surname)
                                              :birthdate birthdate
                                              :insurance-no insurance-no}])
               (reset! values initial-values))]
    [:<>
     [:> Box
      [:> Button {:on-click #(open-modal {:modal-name :add-patient :patient @values})} "Add new patient"]
      [modal {:modal-name :add-patient
              :header "Add patient"
              :body [:form {:on-submit #(save % @values)}
                     [form-group {:id :name
                                  :label "Name"
                                  :type "text"
                                  :values values}]
                     [form-group {:id :surname
                                  :label "Surname"
                                  :type "text"
                                  :values values}]
                     [form-group {:id :birthdate
                                  :label "Birthdate"
                                  :type "date"
                                  :values values}]
                     [form-group {:id :insurance-no
                                  :label "Insurance no"
                                  :type "text"
                                  :values values}]]
              :footer [:<>
                       [:> Button {:variant "light"
                                   :on-click #(rf/dispatch [:close-modal])}
                        "Close"]
                       [:> Button {:on-click #(save % @values)}
                        "Save"]]}]
      [:> Row
       [:> Col [:> Typography {:as "div" :font-weight "bold"} "Name"]]
       [:> Col [:> Typography {:as "div" :font-weight "bold"} "Surname"]]
       [:> Col [:> Typography {:as "div" :font-weight "bold"} "Birthdate"]]
       [:> Col [:> Typography {:as "div" :font-weight "bold"} "Insurance no"]]
       [:> Col]]
      (for [{:keys [id name surname birthdate insurance-no]} (vals patients)]
        ^{:key id}
        [:> Row
         [:> Col name]
         [:> Col surname]
         [:> Col birthdate]
         [:> Col insurance-no]
         [:> Col [:> Button {:on-click
                             #(open-modal {:modal-name :patient-editor
                                           :patient {:id id
                                                     :name name
                                                     :surname surname
                                                     :birthdate birthdate
                                                     :insurance-no insurance-no}})} "Edit"]]])
      [modal {:modal-name :patient-editor
              :header "Edit patient"
              :body [:form {:on-submit #(save % values)}
                     [form-group {:id :name
                                  :label "Name"
                                  :type "text"
                                  :values values}]
                      [form-group {:id :surname
                                  :label "Surname"
                                  :type "text"
                                  :values values}]
                      [form-group {:id :birthdate
                                  :label "Birthdate"
                                  :type "date"
                                  :values values}]
                      [form-group {:id :insurance-no
                                  :label "Insurance No"
                                  :type "text"
                                  :values values}]]
              :footer [:<>
                       [:a {:href "#"
                            :on-click #(when (js/confirm "Are you sure?")
                                                         (rf/dispatch [:delete-patient (:id @values)]))} "Delete"]
                       [:> Button {:variant "light"
                                       :on-click #(rf/dispatch [:close-modal])}
                        "Close"]
                       [:> Button {:on-click #(save % @values)} "Save"]]}]]]))
