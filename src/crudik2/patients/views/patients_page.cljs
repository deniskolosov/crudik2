(ns crudik2.patients.views.patients-page
  (:require [re-frame.core :as rf]
            ["@smooth-ui/core-sc" :refer [Box Button Row Col Typography]]
            [crudik2.components.modal :refer [modal]]))


(defn patients-page
  []
  (let [patients @(rf/subscribe [:patients])
        open-modal (fn [{:keys [modal-name]}]
                     (rf/dispatch [:open-modal modal-name]))]
    [:<>
     [:> Box
      [:> Button {:on-click #(open-modal {:modal-name :add-patient})} "Add new patient"]
      [modal {:modal-name :add-patient
              :header "Add patient"
              :body [:div "Hello add patient"]
              :footer [:<> [:> Button {:variant "light"
                                       :on-click #(rf/dispatch [:close-modal])}
                            "Close"]]}]
      [:> Row
       [:> Col [:> Typography {:as "div" :font-weight "bold"} "Name"]]
       [:> Col [:> Typography {:as "div" :font-weight "bold"} "Surname"]]
       [:> Col [:> Typography {:as "div" :font-weight "bold"} "Birthdate"]]
       [:> Col [:> Typography {:as "div" :font-weight "bold"} "Insurance no"]]
       [:> Col ]]
       (for [{:keys [name surname birthdate insurance-no]} (vals patients)]
         ^{:key insurance-no}
         [:> Row
          [:> Col name]
          [:> Col surname]
          [:> Col birthdate]
          [:> Col insurance-no]
          [:> Col [:> Button {:on-click #(open-modal {:modal-name :patient-editor})} "Edit"]]])
      [modal {:modal-name :patient-editor
              :header "Edit patient"
              :body [:div "Hello"]
              :footer [:<> [:> Button {:variant "light"
                                        :on-click #(rf/dispatch [:close-modal])}
                             "Close"]]}]]]))
