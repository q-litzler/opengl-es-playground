package qlitzler.com.opengl.main;

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

	private final float[] matrixProjection = new float[16];

	private ConfigGrid configGrid;
	private Grid grid;

	public GLRendererMain(ConfigGrid configGrid) {
		this.configGrid = configGrid;
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		GLES30.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		grid = new Grid(new ShaderGrid(), configGrid);
	}

	public void onDrawFrame(GL10 unused) {
		GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);
		grid.draw(matrixProjection);
	}

	public void onSurfaceChanged(GL10 unused, int width, int height) {
		GLES30.glViewport(0, 0, width, height);
		Matrix.setIdentityM(matrixProjection, 0);
		Matrix.orthoM(matrixProjection, 0, 0, width, height, 0, 1, -1);
	}

	public static int loadShader(int type, String shaderCode) {
		int shader = GLES30.glCreateShader(type);

		GLES30.glShaderSource(shader, shaderCode);
		GLES30.glCompileShader(shader);
		return shader;
	}

//	public void zoom(float factor, float step, float way) {
//		Matrix.setIdentityM(matrixScale, 0);
//
//		Matrix.scaleM(matrixScale, 0, factor, factor, 1.0f);
//
//		float width = (configGrid.squareWidth * 3f / step) * way;
//		float height = (configGrid.squareHeight * 7f / step) * way;
//		Matrix.translateM(matrixTranslate, 0, width, height, 0f);
//
//		Matrix.multiplyMM(matrixScale, 0, matrixScale, 0, matrixTranslate, 0);
//		Matrix.multiplyMM(matrixCurrent, 0, matrixMvp, 0, matrixScale, 0);
//	}
}
