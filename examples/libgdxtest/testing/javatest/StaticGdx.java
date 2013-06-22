/*
 * Gdx.gl is static, bust must not be retrieved before first time render is called.
 */

package javatest;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class StaticGdx
implements ApplicationListener {

  public void create() {}
  public void dispose() {}
  public void pause() {}
  public void resize(int width, int height) {}
  public void resume() {}

  public static GLCommon glc;

  public static GLCommon getGlc() {
    if (StaticGdx.glc == null) {
      StaticGdx.glc = Gdx.gl;
    }
    return StaticGdx.glc;
  }

  public void render() {
    StaticGdx.getGlc().glClear(GL10.GL_COLOR_BUFFER_BIT);
  }

  public static void main(String[] args) {
    new LwjglApplication(new StaticGdx(), "static gdx test", 800, 600, false);
  }

}
