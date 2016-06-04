package qlitzler.com.opengl.opengl.object;

import android.opengl.GLES30;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import qlitzler.com.opengl.opengl.shader.Shader;


/**
 * Created by qlitzler on 29/05/16.
 */
public abstract class GLObject<T extends Shader> {

	private static final int INT = 2;
	private static final int FLOAT = 4;

	static final int XYZ = 3;
	static final int RGBA = 4;

	final T shader;

	GLObject(T shader) {
		this.shader = shader;
	}

	public abstract void draw(float[] mvp);

	FloatBuffer generateFloatBuffer(float[] buffer) {
		FloatBuffer floatBuffer = generateBuffer(buffer.length * FLOAT).asFloatBuffer();
		floatBuffer.put(buffer);
		floatBuffer.position(0);
		return floatBuffer;
	}

	ShortBuffer generateShortBuffer(short[] buffer) {
		ShortBuffer shortBuffer = generateBuffer(buffer.length * INT).asShortBuffer();
		shortBuffer.put(buffer);
		shortBuffer.position(0);
		return shortBuffer;
	}

	private ByteBuffer generateBuffer(int size) {
		ByteBuffer buffer = ByteBuffer.allocateDirect(size);
		buffer.order(ByteOrder.nativeOrder());
		return buffer;
	}

	void bindArrayBuffer(int buffer, int attribute, int size, Buffer data, int type) {
		GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, buffer);
		GLES30.glBufferData(GLES30.GL_ARRAY_BUFFER, data.capacity() * FLOAT, data, type);
		GLES30.glEnableVertexAttribArray(attribute);
		GLES30.glVertexAttribPointer(attribute, size, GLES30.GL_FLOAT, false, size * FLOAT, 0);
		GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, 0);
	}

	void bindElementBuffer(int buffer, Buffer data, int type) {
		GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, buffer);
		GLES30.glBufferData(GLES30.GL_ELEMENT_ARRAY_BUFFER, data.capacity() * INT, data, type);
		GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, 0);
	}
}
