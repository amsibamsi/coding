(ns lwjgltest.core
  (:import (org.lwjgl.opengl Display DisplayMode GL11)))

(def fps
  60)

(defn init []
  (Display/setDisplayMode (new DisplayMode 800 600))
  (Display/create)
  (GL11/glViewport 100 100 800 600)
  (GL11/glMatrixMode GL11/GL_PROJECTION)
  (GL11/glLoadIdentity)
  (GL11/glOrtho 0 800 0 600 -1 1))

(defn render []
  (GL11/glClear GL11/GL_COLOR_BUFFER_BIT)
  (GL11/glMatrixMode GL11/GL_MODELVIEW)
  (GL11/glBegin GL11/GL_QUADS)
  (GL11/glColor3f 1.0 1.0 1.0)
  (GL11/glVertex2i   0.0   0.0)
  (GL11/glVertex2i 100.0   0.0)
  (GL11/glVertex2i 100.0 100.0)
  (GL11/glVertex2i   0.0 100.0)
  (GL11/glEnd))

(defn game-loop []
  (render)
  (Display/update)
  (Display/sync fps)
  (if (Display/isCloseRequested)
    (Display/destroy)
    (recur)))

(defn main []
  (init)
  (game-loop))
