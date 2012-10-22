(ns jmetest.core
  (:import (com.jme3.app SimpleApplication)
           (com.jme3.system JmeSystem)
           (com.jme3.material Material)
           (com.jme3.math Vector3f)
           (com.jme3.scene Geometry)
           (com.jme3.scene.shape Sphere)
           (com.jme3.math ColorRGBA)
           (com.jme3.light DirectionalLight AmbientLight)
           (com.jme3.util SkyFactory))
  (:gen-class
    :extends com.jme3.app.SimpleApplication))

(def asset-url (.getResource (.getContextClassLoader (Thread/currentThread)) "com/jme3/asset/Desktop.cfg"))
(def asset-manager (JmeSystem/newAssetManager asset-url))
(def sphere (new Sphere 32 32 1))
(def geometry (new Geometry "Sphere" sphere))
(def material (new Material asset-manager "Common/MatDefs/Light/Lighting.j3md"))
(def light (new Vector3f 1 1 -1))
(def sun (new DirectionalLight))
(def sky (SkyFactory/createSky asset-manager "Textures/Sky/Bright/BrightSky.dds" false))
(def ambient (new AmbientLight))
(def gravity-center (new Vector3f 0 0 0))
(def velocity (new Vector3f 0.9 0 0))

(defn -simpleInitApp [this]
  (.setBoolean material "UseMaterialColors" true)
  (.setColor material "Ambient" ColorRGBA/Red)
  (.setColor material "Diffuse" ColorRGBA/Red)
  (.setColor material "Specular" ColorRGBA/White)
  (.setFloat material "Shininess" 10)
  (.setMaterial geometry material)
  (.setLocalTranslation geometry 0 2 -2)
  (.attachChild (.getRootNode this) geometry)
  (.setDirection sun light)
  (.setColor sun ColorRGBA/White)
  (.addLight (.getRootNode this) sun)
  (.attachChild (.getRootNode this) sky)
  (.setColor ambient ColorRGBA/Gray)
  (.addLight (.getRootNode this) ambient))

(defn -simpleUpdate [this tpf]
  (let
      [time (* tpf 10.0)
       location (.getLocalTranslation geometry)
       distance (.distance location gravity-center)
       direction (.negate location)
       acceleration (* time (/ 1.0 (* distance distance)))]
    (.add velocity (.mult direction (float acceleration)) velocity)
    (.move geometry (.mult velocity (float time)))
    (println velocity)))

(defn -main [& args]
  (doto (new jmetest.core) (.start)))
