package qlitzler.com.opengl;

import android.opengl.GLES30;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by qlitzler on 29/05/16.
 */
public class Square extends GLObject {

	private int[] vao;
	private int[] buffers;

	private static final int VBO = 0;
	private static final int CBO = 1;
	private static final int EBO = 2;
	private static final int IBO = 3;

	private final int vPosition, vColor, vInstance;

	private FloatBuffer vertices;
	private FloatBuffer colors;
	private FloatBuffer instance;
	private ShortBuffer elements;

	private static final int INT = 2;
	private static final int FLOAT = 4;

	private static final int XYZ = 3;
	private static final int RGBA = 4;


	public static final float COORDS1[] = {
		-0.2f, 0.2f, 0.0f,
		-0.2f, -0.2f, 0.0f,
		0.2f, -0.2f, 0.0f,
		0.2f, 0.2f, 0.0f,
	};

	public static final float COORDS2[] = {
		-0.5f, 0.5f, 0.0f,
		-0.5f, -0.5f, 0.0f,
		0.5f, -0.5f, 0.0f,
		0.5f, 0.5f, 0.0f,
	};

	public static final float POS1[] = {
		0.4f, 0.0f, 0.0f,
		0,0f, 0.0f, 0.0f,
	};

	public static final float POS2[] = {
		0.0f, 0.0f, 0.0f,
	};

	public static final float[] BLACK = {
		0.0f, 0.0f, 0.5f, 0.0f,
		0.5f, 0.0f, 0.0f, 0.0f,
		0.0f, 0.5f, 0.0f, 0.0f,
		0.5f, 0.5f, 0.5f, 0.0f,
		0.0f, 0.0f, 0.0f, 0.0f,
		0.0f, 0.0f, 0.0f, 0.0f,
		0.0f, 0.0f, 0.0f, 0.0f,
		0.0f, 0.0f, 0.0f, 0.0f,
	};

	public static final float[] GREY = {
		0.0f, 0.0f, 0.5f, 0.0f,
		0.5f, 0.0f, 0.0f, 0.0f,
		0.0f, 0.5f, 0.0f, 0.0f,
		0.5f, 0.5f, 0.5f, 0.0f
	};

	private static final short ELEMENTS[] = {0, 1, 2, 0, 2, 3};

	public Square(float[] coords, float[] floats, float[] pos) {
		super();
		vao = new int[1];
		buffers = new int[4];

		vPosition = GLES30.glGetAttribLocation(shader, "v_position");
		vColor = GLES30.glGetAttribLocation(shader, "v_color");
		vInstance = GLES30.glGetAttribLocation(shader, "v_instance");

		vertices = generateFloatBuffer(coords, coords.length * FLOAT);
		colors = generateFloatBuffer(floats, floats.length * FLOAT);
		instance = generateFloatBuffer(pos, pos.length * FLOAT);
		elements = generateShortBuffer(ELEMENTS, ELEMENTS.length * INT);

		GLES30.glGenVertexArrays(1, vao, 0);
		GLES30.glBindVertexArray(vao[0]);
		GLES30.glGenBuffers(4, buffers, 0);

		GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, buffers[VBO]);
		GLES30.glBufferData(GLES30.GL_ARRAY_BUFFER, coords.length * FLOAT, vertices, GLES30.GL_STATIC_DRAW);
		GLES30.glEnableVertexAttribArray(vPosition);
		GLES30.glVertexAttribPointer(vPosition, XYZ, GLES30.GL_FLOAT, false, XYZ * FLOAT, 0);

		GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, buffers[CBO]);
		GLES30.glBufferData(GLES30.GL_ARRAY_BUFFER, floats.length * FLOAT, colors, GLES30.GL_STATIC_DRAW);
		GLES30.glEnableVertexAttribArray(vColor);
		GLES30.glVertexAttribPointer(vColor, RGBA, GLES30.GL_FLOAT, false, RGBA * FLOAT, 0);

		GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, buffers[IBO]);
		GLES30.glBufferData(GLES30.GL_ARRAY_BUFFER, pos.length * FLOAT, instance, GLES30.GL_STREAM_DRAW);
		GLES30.glEnableVertexAttribArray(vInstance);
		GLES30.glVertexAttribPointer(vInstance, XYZ, GLES30.GL_FLOAT, false, XYZ * FLOAT, 0);

		GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, buffers[EBO]);
		GLES30.glBufferData(GLES30.GL_ELEMENT_ARRAY_BUFFER, ELEMENTS.length * INT, elements, GLES30.GL_STATIC_DRAW);

		GLES30.glVertexAttribDivisor(vPosition, 0);
		GLES30.glVertexAttribDivisor(vColor, 0);
		GLES30.glVertexAttribDivisor(vInstance, 1);
	}

	public void draw() {

		GLES30.glUseProgram(shader);
//		GLES30.glBindVertexArray(vao[0]);
		GLES30.glDrawElementsInstanced(GLES30.GL_TRIANGLE_STRIP, ELEMENTS.length, GLES30.GL_UNSIGNED_SHORT, 0, 2);
	}

	@Override
	protected String getShaderVertex() {
		return " #version 300 es\n" +
			"in vec4 v_position;" +
			"in vec4 v_color;" +
			"in vec4 v_instance;" +
			"out vec4 f_color;" +
			"void main() {" +
			"	gl_Position = v_position + v_instance;" +
			"	f_color = v_color;" +
			"}";
	}

	@Override
	protected String getShaderFragment() {
		return " #version 300 es\n" +
			"in vec4 f_color;" +
			"out vec4 outColor;" +
			"void main() {" +
			"  outColor = f_color;" +
			"}";
	}
}
