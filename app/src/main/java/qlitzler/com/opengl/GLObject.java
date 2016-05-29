package qlitzler.com.opengl;

import android.opengl.GLES30;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by qlitzler on 29/05/16.
 */
public abstract class GLObject {

	protected final int shader;

	public GLObject() {
		shader = shader();
	}

	protected abstract String getShaderVertex();

	protected abstract String getShaderFragment();

	private int shader() {
		final int vertexShader = GLRendererMain.loadShader(GLES30.GL_VERTEX_SHADER, getShaderVertex());
		final int fragmentShader = GLRendererMain.loadShader(GLES30.GL_FRAGMENT_SHADER, getShaderFragment());
		final int shader = GLES30.glCreateProgram();

		GLES30.glAttachShader(shader, vertexShader);
		GLES30.glAttachShader(shader, fragmentShader);
		GLES30.glLinkProgram(shader);
		return shader;
	}

	protected FloatBuffer generateFloatBuffer(float[] buffer, int size) {
		FloatBuffer floatBuffer = generateBuffer(size).asFloatBuffer();
		floatBuffer.put(buffer);
		floatBuffer.position(0);
		return floatBuffer;
	}

	protected ShortBuffer generateShortBuffer(short[] buffer, int size) {
		ShortBuffer shortBuffer = generateBuffer(size).asShortBuffer();
		shortBuffer.put(buffer);
		shortBuffer.position(0);
		return shortBuffer;
	}

	protected ByteBuffer generateBuffer(int size) {
		ByteBuffer buffer = ByteBuffer.allocateDirect(size);
		buffer.order(ByteOrder.nativeOrder());
		return buffer;
	}
}
