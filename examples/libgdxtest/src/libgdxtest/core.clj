(ns libgdxtest.core
  (:import (com.badlogic.gdx ApplicationListener Gdx)
           (com.badlogic.gdx.graphics GL10 Mesh VertexAttribute)
           (com.badlogic.gdx.backends.lwjgl LwjglApplication)))

(defn app-listener []
  (let [vertices (float-array [-0.5 -0.5 0 0.5 -0.5 0 0 0.5 0])
        triangles (into-array Short/TYPE [0 1 2])
        attrs (into-array VertexAttribute [(VertexAttribute.
                                            com.badlogic.gdx.graphics.VertexAttributes$Usage/Position
                                            3 "a_position")])
        mesh (ref nil)]
    (proxy [ApplicationListener] []
      (resize [w h])
      (create []
        (let [m (doto (Mesh. true 3 3 attrs)
                      (.setVertices vertices)
                      (.setIndices triangles))]
        (dosync ( ref-set mesh m))))
      (render []
        (doto (Gdx/gl)
              (.glClear GL10/GL_COLOR_BUFFER_BIT))
        (doto @mesh
          (.render GL10/GL_TRIANGLES 0 3)))
      (pause [] )
      (resume [] )
      (dispose [] ))))

(defn -main [& args]
  (LwjglApplication. (app-listener) "libgdx test" 480 320 false))
