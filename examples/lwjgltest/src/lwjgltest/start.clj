(ns lwjgltest.start
  (:require lwjgltest.core)
  (:require lwjgltest.util)
  (:gen-class))

(defn -main []
  (lwjgltest.util/print-env)
  (lwjgltest.core/main))
