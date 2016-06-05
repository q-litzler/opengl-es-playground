package qlitzler.com.opengl.main;

import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import qlitzler.com.opengl.opengl.object.ConfigGrid;
import qlitzler.com.opengl.opengl.object.Grid;
import qlitzler.com.opengl.opengl.shader.ShaderGrid;

/**
 * Created by qlitzler on 29/05/16.
 */
public class GLRendererMain implements GLSurfaceView.Renderer {

	private Grid grid;

	private final float[] matrixMvp = new float[16];
	private final float[] matrixProjection = new float[16];
	private final float[] matrixView = new float[16];

	private final float[] square, position, colors;
	private final int instance;

	public GLRendererMain(float[] square, float[] position, float[] colors, int instance) {
		this.square = square;
		this.position = position;
		this.colors = colors;
		this.instance = instance;
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		GLES30.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		grid = Grid.newInstance(new ShaderGrid(), ConfigGrid.newInstance(square, position, colors, instance));
	}

	public void onDrawFrame(GL10 unused) {
		GLES30.glClear(GLES20.GL_COLOR_BUFFER_BIT);
		grid.draw(matrixProjection);
	}

	public void onSurfaceChanged(GL10 unused, int width, int height) {
		GLES30.glViewport(0, 0, width, height);
		Matrix.setIdentityM(matrixProjection, 0);
		Matrix.orthoM(matrixProjection, 0, 0, width, height, 0, 1, -1);
//		Matrix.setLookAtM(matrixView, 0, 0f, 0f, 1f, 0f, 0f, 0f, 0f, 1f, 0f);
//		Matrix.multiplyMM(matrixMvp, 0, matrixProjection, 0, matrixView, 0);
	}

	public static int loadShader(int type, String shaderCode) {
		int shader = GLES30.glCreateShader(type);

		GLES30.glShaderSource(shader, shaderCode);
		GLES30.glCompileShader(shader);
		return shader;
	}
}
