(ns crudik2.patients.views.patients-page
  (:require [re-frame.core :as rf]))



(defn patients-page
  []
  [:table
   [:tr
    [:th "Namee"]
    [:th "Surname"]
    [:th "Birthhdate"]
    [:th "Insurance no"]]
   [:tr
    [:td "Ivan"]
    [:td "Ivanov"]
    [:td "1990-10-10"]
    [:td "Foobar123"]]])
