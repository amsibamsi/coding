(ns jmetest.core
  (:import (com.jme3.app SimpleApplication)
           (com.jme3.system JmeSystem)
           (com.jme3.material Material)
           (com.jme3.math Vector3f)
           (com.jme3.scene Geometry)
           (com.jme3.scene.shape Box)
           (com.jme3.math ColorRGBA)
           (com.jme3.util SkyFactory))
  (:gen-class
    :extends com.jme3.app.SimpleApplication))

(def asset-url (.getResource (.getContextClassLoader (Thread/currentThread)) "com/jme3/asset/Desktop.cfg"))
(def asset-manager (JmeSystem/newAssetManager asset-url))
(def box (new Box Vector3f/ZERO 1 1 1))
(def geometry (new com.jme3.scene.Geometry "Box" box))
(def material (new Material asset-manager "Common/MatDefs/Misc/Unshaded.j3md"))
(def sky (SkyFactory/createSky asset-manager "Textures/Sky/Bright/BrightSky.dds" false))

(defn -simpleInitApp [this]
  (.setColor material "m_Color" ColorRGBA/Blue)
  (.setMaterial geometry material)
  (.attachChild (.getRootNode this) geometry)
  (.attachChild (.getRootNode this) sky))

(defn -main [& args]
  (doto (new jmetest.core) (.start)))
