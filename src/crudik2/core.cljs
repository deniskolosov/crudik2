(ns crudik2.core
    (:require
      [reagent.core :as r]
      [reagent.dom :as d]
      [crudik2.patients.views.patients-page :refer [patients-page]]))


(defn mount-root []
  (d/render [patients-page] (.getElementById js/document "app")))

(defn ^:export init! []
  (mount-root))
