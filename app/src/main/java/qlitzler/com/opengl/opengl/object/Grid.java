package qlitzler.com.opengl.opengl.object;

import android.opengl.GLES30;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import qlitzler.com.opengl.opengl.shader.ShaderGrid;

/**
 * Created by qlitzler on 29/05/16.
 */
public class Grid extends GLObject<ShaderGrid> {

	private final int[] vao;
	private final int[] buffers;

	private static final int VBO = 0;
	private static final int CBO = 1;
	private static final int EBO = 2;
	private static final int IBO = 3;

	private ConfigGrid configGrid;

	public Grid(ShaderGrid shaderGrid, ConfigGrid configGrid) {
		super(shaderGrid);
		this.configGrid = configGrid;
		vao = new int[1];
		buffers = new int[4];

		FloatBuffer vertices = generateFloatBuffer(configGrid.vertices);
		FloatBuffer colors = generateFloatBuffer(configGrid.colors);
		FloatBuffer instance = generateFloatBuffer(configGrid.instances);
		ShortBuffer indices = generateShortBuffer(configGrid.indices);

		GLES30.glGenVertexArrays(1, vao, 0);
		GLES30.glBindVertexArray(vao[0]);
		GLES30.glGenBuffers(4, buffers, 0);

		bindArrayBuffer(buffers[VBO], shaderGrid.position, XY, vertices, GLES30.GL_STATIC_DRAW);
		bindArrayBuffer(buffers[CBO], shaderGrid.color, RGBA, colors, GLES30.GL_STATIC_DRAW);
		bindArrayBuffer(buffers[IBO], shaderGrid.instance, XY, instance, GLES30.GL_STATIC_DRAW);
		bindElementBuffer(buffers[EBO], indices, GLES30.GL_STATIC_DRAW);

		GLES30.glVertexAttribDivisor(shaderGrid.position, 0);
		GLES30.glVertexAttribDivisor(shaderGrid.color, 1);
		GLES30.glVertexAttribDivisor(shaderGrid.instance, 1);

		GLES30.glBindVertexArray(0);
	}

	@Override
	public void draw(float[] mvp) {
		GLES30.glUseProgram(shader.program);
		GLES30.glUniformMatrix4fv(shader.mvp, 1, false, mvp, 0);
		GLES30.glBindVertexArray(vao[0]);
		GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, buffers[EBO]);
		GLES30.glDrawElementsInstanced(GLES30.GL_TRIANGLE_STRIP, configGrid.indices.length, GLES30.GL_UNSIGNED_SHORT, 0, configGrid.instance);
		GLES30.glBindVertexArray(0);
		GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, 0);
	}
}
