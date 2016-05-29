package qlitzler.com.opengl;

import android.opengl.GLES30;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by qlitzler on 29/05/16.
 */
public class Square extends GLObject {

	private int vao;
	private int vbo;
	private int ebo;
	private int cbo;

	private final int position;
	private final int color;

	private FloatBuffer vertices;
	private ShortBuffer elements;

	private static final int DIMENSIONS = 3;
	private static final int STRIDE = DIMENSIONS * 4;

	public static final float COORDS1[] = {
		-0.2f, 0.2f, 0.0f,
		-0.2f, -0.2f, 0.0f,
		0.2f, -0.2f, 0.0f,
		0.2f, 0.2f, 0.0f
	};

	public static final float COORDS2[] = {
		-0.5f, 0.5f, 0.0f,
		-0.5f, -0.5f, 0.0f,
		0.5f, -0.5f, 0.0f,
		0.5f, 0.5f, 0.0f
	};

	private static final short ELEMENTS[] = {0, 1, 2, 0, 2, 3};

	public Square(float[] coords) {
		super();
		position = GLES30.glGetAttribLocation(shader, "vPosition");
		color = GLES30.glGetUniformLocation(shader, "vColor");

		vertices = generateFloatBuffer(coords, coords.length * 4);
		elements = generateShortBuffer(ELEMENTS, ELEMENTS.length * 2);

//		IntBuffer intBuffer = IntBuffer.allocate();

//		GLES30.glGenVertexArrays(1, vao, 0);
		GLES30.glBindVertexArray(vao);
		GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, vbo);
		GLES30.glBufferData(GLES30.GL_ARRAY_BUFFER, coords.length, vertices, GLES30.GL_STATIC_DRAW);
		GLES30.glVertexAttribPointer(position, DIMENSIONS, GLES30.GL_FLOAT, false, STRIDE, vertices);

		GLES30.glBindVertexArray(0);
		GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, 0);
	}

	public void draw(float[] floats) {

		GLES30.glUseProgram(shader);
		GLES30.glEnableVertexAttribArray(position);
		GLES30.glUniform4fv(color, 1, floats, 0);
		GLES30.glBindVertexArray(vao);
		GLES30.glDrawElements(GLES30.GL_TRIANGLE_STRIP, ELEMENTS.length, GLES30.GL_UNSIGNED_SHORT, elements);
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
