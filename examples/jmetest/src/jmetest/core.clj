(ns jmetest.core
  (:import (com.jme3.app SimpleApplication)
           (com.jme3.system JmeSystem)
           (com.jme3.material Material)
           (com.jme3.math Vector3f)
           (com.jme3.scene Geometry)
           (com.jme3.scene.shape Sphere)
           (com.jme3.math ColorRGBA)
           (com.jme3.light DirectionalLight)
           (com.jme3.util SkyFactory))
  (:gen-class
    :extends com.jme3.app.SimpleApplication))

(def asset-url (.getResource (.getContextClassLoader (Thread/currentThread)) "com/jme3/asset/Desktop.cfg"))
(def asset-manager (JmeSystem/newAssetManager asset-url))
(def sphere (new Sphere 32 32 2))
(def geometry (new Geometry "Box" sphere))
(def material (new Material asset-manager "Common/MatDefs/Light/Lighting.j3md"))
(def light (new Vector3f 1 1 1))
(def sun (new DirectionalLight))
(def sky (SkyFactory/createSky asset-manager "Textures/Sky/Bright/BrightSky.dds" false))

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
  (.attachChild (.getRootNode this) sky))

(defn -simpleUpdate [this tpf]
  (.rotate geometry 0 0 (* 0.5 tpf))
  ())

(defn -main [& args]
  (doto (new jmetest.core) (.start)))
