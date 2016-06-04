package qlitzler.com.opengl;

import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import qlitzler.com.opengl.opengl.ConfigGrid;
import qlitzler.com.opengl.opengl.Grid;
import qlitzler.com.opengl.shader.ShaderGrid;

/**
 * Created by qlitzler on 29/05/16.
 */
public class GLRendererMain implements GLSurfaceView.Renderer {

	private Grid grid, grid1;

	private final float[] matrixMvp = new float[16];
	private final float[] matrixProjection = new float[16];
	private final float[] matrixView = new float[16];

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		GLES30.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		grid = Grid.newInstance(new ShaderGrid(), ConfigGrid.newInstance(), 2);
	}

	public void onDrawFrame(GL10 unused) {
		GLES30.glClear(GLES20.GL_COLOR_BUFFER_BIT);
		Matrix.setLookAtM(matrixView, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
		Matrix.multiplyMM(matrixMvp, 0, matrixProjection, 0, matrixView, 0);

		grid.draw(matrixMvp);
	}

	public void onSurfaceChanged(GL10 unused, int width, int height) {
		GLES30.glViewport(0, 0, width, height);
		float ratio = (float) width / height;
		Matrix.orthoM(matrixProjection, 0, -ratio, ratio, -1, 1, 3, 7);
	}

	public static int loadShader(int type, String shaderCode) {
		int shader = GLES30.glCreateShader(type);

		GLES30.glShaderSource(shader, shaderCode);
		GLES30.glCompileShader(shader);
		return shader;
	}
}
