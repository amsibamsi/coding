(ns lwjgltest.util
  (:require clojure.pprint))

(defn print-env []
  (println "classpath")
  (clojure.pprint/pprint (.getURLs (java.lang.ClassLoader/getSystemClassLoader)))
  (println "library path")
  (clojure.pprint/pprint (System/getProperty "java.library.path"))
  (println "lwjgl library path")
  (clojure.pprint/pprint (System/getProperty "org.lwjgl.librarypath"))
  (println "tmpdir")
  (clojure.pprint/pprint (System/getProperty "java.io.tmpdir"))
  (println "end"))

