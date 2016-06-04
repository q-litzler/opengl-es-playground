package qlitzler.com.opengl;

import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static qlitzler.com.opengl.Square.BLACK;
import static qlitzler.com.opengl.Square.POS1;

/**
 * Created by qlitzler on 29/05/16.
 */
public class GLRendererMain implements GLSurfaceView.Renderer {

	private Square square1, square2;

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		GLES30.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		square1 = new Square(Square.COORDS1, BLACK, POS1);
//		square2 = new Square(Square.COORDS2, GREY, POS2);
	}

	public void onDrawFrame(GL10 unused) {
		GLES30.glClear(GLES20.GL_COLOR_BUFFER_BIT);

//		square2.draw();
		square1.draw();
	}

	public void onSurfaceChanged(GL10 unused, int width, int height) {
		GLES30.glViewport(0, 0, width, height);
	}

	public static int loadShader(int type, String shaderCode) {
		int shader = GLES30.glCreateShader(type);

		GLES30.glShaderSource(shader, shaderCode);
		GLES30.glCompileShader(shader);
		return shader;
	}
}
