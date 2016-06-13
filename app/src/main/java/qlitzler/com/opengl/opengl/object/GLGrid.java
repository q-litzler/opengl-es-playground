package qlitzler.com.opengl.opengl.object;

import android.graphics.Color;
import android.opengl.GLES30;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import qlitzler.com.opengl.AppOpenGL;
import qlitzler.com.opengl.opengl.shader.ShaderGrid;

/**
 * Created by qlitzler on 29/05/16.
 */
public class GLGrid extends GLObject<ShaderGrid> {

	private final int[] vao;
	private final int[] buffers;

	private static final int VBO = 0;
	private static final int CBO = 1;
	private static final int EBO = 2;
	private static final int IBO = 3;

	private Grid grid;

	public GLGrid(ShaderGrid shaderGrid, Grid grid) {
		super(shaderGrid);
		this.grid = grid;
		vao = new int[1];
		buffers = new int[4];

		FloatBuffer vertices = generateFloatBuffer(vertices(grid));
		FloatBuffer colors = generateFloatBuffer(colors(grid));
		FloatBuffer instance = generateFloatBuffer(instances(grid));
		ShortBuffer indices = generateShortBuffer(ELEMENTS);

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
		GLES30.glDrawElementsInstanced(GLES30.GL_TRIANGLE_STRIP, ELEMENTS.length, GLES30.GL_UNSIGNED_SHORT, 0, grid.map.size);
		GLES30.glBindVertexArray(0);
		GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER, 0);
	}

	public static float[] vertices(Grid grid) {
		float x = grid.squareWidth;
		float y = grid.squareHeight;
		return new float[]{
			0, y,
			0, 0,
			x, 0,
			x, y,
		};
	}

	private static final short ELEMENTS[] = {0, 1, 2, 0, 2, 3};

	public static float[] colors(Grid grid) {
		float[] position = new float[grid.map.size * RGBA];

		for (int i = 0; i < grid.map.size; ++i) {
			final int index = i * RGBA;
			final int color = AppOpenGL.getColorUtils().getColor(grid.map.bytes[i]);

			position[index] = Color.red(color) / 255.0f;
			position[index + 1] = Color.green(color) / 255.0f;
			position[index + 2] = Color.blue(color) / 255.0f;
			position[index + 3] = 0.0f;
		}
		return position;
	}

	public static float[] instances(Grid grid) {
		float[] position = new float[grid.map.size * XY];
		int indexX = 0;
		int indexY = 0;

		for (int i = 0; i < grid.map.size; ++i) {
			if (i != 0 && i % grid.map.row == 0) {
				indexX = 0;
				++indexY;
			}
			final int index = i * XY;

			position[index] = grid.squareWidth * indexX;
			position[index + 1] = grid.squareHeight * indexY;
			++indexX;
		}
		return position;
	}
}
