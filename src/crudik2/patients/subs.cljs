(ns crudik2.patients.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
 :patients
 (fn [db _]
   (get db :patients)))


