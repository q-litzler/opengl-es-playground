package qlitzler.com.opengl;

import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by qlitzler on 29/05/16.
 */
public class GLRendererMain implements GLSurfaceView.Renderer {

	private Square square1, square2;

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		GLES30.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		square2 = new Square(Square.COORDS2);
		square1 = new Square(Square.COORDS1);
	}

	public void onDrawFrame(GL10 unused) {
		GLES30.glClear(GLES20.GL_COLOR_BUFFER_BIT);
		float[] black = {0.0f, 0.0f, 0.0f, 0.0f};
		float[] grey = {0.5f, 0.5f, 0.5f, 0.5f};

		square1.draw(black);
		square2.draw(grey);
	}

	public void onSurfaceChanged(GL10 unused, int width, int height) {
		GLES30.glViewport(0, 0, width, height);
	}

	public static int loadShader(int type, String shaderCode) {
		int shader = GLES20.glCreateShader(type);

		GLES20.glShaderSource(shader, shaderCode);
		GLES20.glCompileShader(shader);
		return shader;
	}
}
