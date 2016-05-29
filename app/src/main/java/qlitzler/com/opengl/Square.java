package qlitzler.com.opengl;

import android.opengl.GLES30;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by qlitzler on 29/05/16.
 */
public class Square extends GLObject {

	private FloatBuffer vbo;
	private ShortBuffer ebo;

	private static final int DIMENSIONS = 3;
	private static final int STRIDE = DIMENSIONS * 4;

	private static final float COORDS[] = {
		-0.5f, 0.5f, 0.0f,
		-0.5f, -0.5f, 0.0f,
		0.5f, -0.5f, 0.0f,
		0.5f, 0.5f, 0.0f
	};
	private static final short ELEMENTS[] = {0, 1, 2, 0, 2, 3};

	public Square() {
		super();
		vbo = generateFloatBuffer(COORDS, COORDS.length * 4);
		ebo = generateShortBuffer(ELEMENTS, ELEMENTS.length * 2);
	}

	public void draw() {
		final float[] black = {0.0f, 0.0f, 0.0f, 0.0f};
		final int position = GLES30.glGetAttribLocation(shader, "vPosition");
		final int color = GLES30.glGetUniformLocation(shader, "vColor");

		GLES30.glUseProgram(shader);
		GLES30.glEnableVertexAttribArray(position);
		GLES30.glVertexAttribPointer(position, DIMENSIONS, GLES30.GL_FLOAT, false, STRIDE, vbo);
		GLES30.glUniform4fv(color, 1, black, 0);
		GLES30.glDrawElements(GLES30.GL_TRIANGLE_STRIP, ELEMENTS.length, GLES30.GL_UNSIGNED_SHORT, ebo);
		GLES30.glDisableVertexAttribArray(position);
	}

	@Override
	protected String getShaderVertex() {
		return "attribute vec4 vPosition;" +
			"void main() {" +
			"  gl_Position = vPosition;" +
			"}";
	}

	@Override
	protected String getShaderFragment() {
		return "precision mediump float;" +
			"uniform vec4 vColor;" +
			"void main() {" +
			"  gl_FragColor = vColor;" +
			"}";
	}
}
