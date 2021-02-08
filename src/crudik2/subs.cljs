(ns crudik2.subs
  (:require [re-frame.core :refer [reg-sub]]))


(reg-sub
 :active-modal
 (fn [db _]
   (get db :active-modal)))
