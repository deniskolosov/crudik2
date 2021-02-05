(ns app.db
  (:require [re-frame.core :as rf]))


(def initial-app-db {:patients {1 {:name "Ivan"
                                   :surname "Ivanov"
                                   :birthdate "1990-10-10"
                                   :insurance-no "F3O1O3B3A7R"}
                                2 {:name "Petr"
                                   :surname "Petrov"
                                   :birthdate "1991-11-11"
                                   :insurance-no "F4O1O3B3A7R"}
                                3 {:name "Sidor"
                                   :surname "Sidorov"
                                   :birthdate "1992-12-12"
                                   :insurance-no "F3O5O3B3A7R"}}})
