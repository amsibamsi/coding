(defproject lwjgltest "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.5.0"]
                 [org.lwjgl.lwjgl/lwjgl "2.9.0"]
                 [org.lwjgl.lwjgl/lwjgl-platform "2.9.0" :classifier "natives-osx" :native-prefix ""]]
  :min-lein-version "2.1.3"
  :javac-target "1.6"
  :main lwjgltest.start)
