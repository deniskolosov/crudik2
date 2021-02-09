(ns crudik2.core
    (:require
      [reagent.core :as r]
      [re-frame.core :as rf]
      [reagent.dom :as d]
      [crudik2.patients.subs]
      [crudik2.patients.events]
      [crudik2.db]
      [crudik2.subs]
      [crudik2.events]
      [crudik2.patients.views.patients-page :refer [patients-page]]
      ["@smooth-ui/core-sc" :refer [Normalize Row Col Grid]]))

(defn app
  []
  [:<>
   [:> Normalize]
   [patients-page]])


(defn mount-root []
  (d/render [app] (.getElementById js/document "app")))

(defn ^:export init! []
  (rf/dispatch-sync [:initialize-db])
  (mount-root))

