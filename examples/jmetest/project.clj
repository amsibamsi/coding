(defproject jmetest "0.1.0-SNAPSHOT"
  :description "jmetest"
  :dependencies [
    [org.clojure/clojure "1.4.0"]
    [com.jme3/jME3-core "3.0.0-2012-10-21"]
    [com.jme3/jME3-desktop "3.0.0-2012-10-21"]
    [com.jme3/jME3-lwjgl "3.0.0-2012-10-21"]
    [com.jme3/jME3-lwjgl-natives "3.0.0-2012-10-21"]
    [com.jme3/lwjgl "3.0.0-2012-10-21"]
    [com.jme3/jME3-testdata "3.0.0-2012-10-21"]
  ]
  :min-lein-version "2.0.0"
  :javac-target "1.6"
  :main jmetest.core
)
