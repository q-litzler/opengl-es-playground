package qlitzler.com.opengl.opengl.object;

import android.graphics.Color;

import qlitzler.com.opengl.AppOpenGL;

import static qlitzler.com.opengl.opengl.object.GLObject.RGBA;
import static qlitzler.com.opengl.opengl.object.GLObject.XY;

/**
 * Created by qlitzler on 04/06/16.
 */

public class ConfigGrid {

	final byte[] grid;
	final float[] vertices;
	final float[] colors;
	final float[] instances;
	final short[] indices;

	public final int instance;

	public static ConfigGrid newInstance(byte[] grid, int total, int width, int height) {
		final int row = (int) Math.sqrt(total);
		float x = width / (float) row;
		float y = height / (float) row;

		return new ConfigGrid(
			grid,
			ConfigGrid.vertices(x, y),
			ConfigGrid.colors(total, grid),
			ELEMENTS,
			ConfigGrid.instances(x, y, row, total),
			total
		);
	}

	private ConfigGrid(byte[] grid, float[] vertices, final float[] colors, final short[] indices, final float[] instances, int instance) {
		this.grid = grid;
		this.vertices = vertices;
		this.instances = instances;
		this.colors = colors;
		this.indices = indices;
		this.instance = instance;
	}

	public static float[] vertices(float x, float y) {
		return new float[]{
			0, y,
			0, 0,
			x, 0,
			x, y,
		};
	}

	private static final short ELEMENTS[] = {0, 1, 2, 0, 2, 3};

	public static float[] colors(int total, byte[] grid) {
		float[] position = new float[total * RGBA];

		for (int i = 0; i < total; ++i) {
			final int index = i * RGBA;
			final int color = AppOpenGL.getColorUtils().getColor(grid[i]);
			final float black = i / (float)total;
			final int gradient;

			if (color == AppOpenGL.getColorUtils().yellow) {

			}

//			position[index] = black;
//			position[index + 1] = black;
//			position[index + 2] = black;
//			position[index + 3] = 0.0f;

			position[index] = Color.red(color) / 255.0f;
			position[index + 1] = Color.green(color) / 255.0f;
			position[index + 2] = Color.blue(color) / 255.0f;
			position[index + 3] = 0.0f;
		}
		return position;
	}

	public static float[] instances(float x, float y, int row, int total) {
		float[] position = new float[total * XY];
		int indexX = 0;
		int indexY = 0;

		for (int i = 0; i < total; ++i) {
			if (i != 0 && i % row == 0) {
				indexX = 0;
				++indexY;
			}
			final int index = i * XY;

			position[index] = x * indexX;
			position[index + 1] = y * indexY;
			++indexX;
		}
		return position;
	}
}
