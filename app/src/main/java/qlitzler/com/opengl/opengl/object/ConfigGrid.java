package qlitzler.com.opengl.opengl.object;

import android.graphics.Color;

import qlitzler.com.opengl.AppOpenGL;

import static qlitzler.com.opengl.opengl.object.GLObject.RGBA;
import static qlitzler.com.opengl.opengl.object.GLObject.XY;

/**
 * Created by qlitzler on 04/06/16.
 */

public class ConfigGrid {

	public final byte[] grid;
	public final float[] vertices;
	public final float[] colors;
	public final float[] instances;
	public final short[] indices;
	public final float squareWidth;
	public final float squareHeight;
	public final int row;
	public final int instance;

	public static ConfigGrid newInstance(byte[] grid, int instance, int width, int height) {
		final int row = (int) Math.sqrt(instance);

		return new ConfigGrid(
			instance,
			row,
			width / (float) row,
			height / (float) row,
			grid,
			ELEMENTS
		);
	}

	private ConfigGrid(int instance, int row, float squareWidth, float squareHeight, byte[] grid, final short[] indices) {
		this.instance = instance;
		this.row = row;
		this.squareWidth = squareWidth;
		this.squareHeight = squareHeight;
		this.grid = grid;
		this.indices = indices;
		this.vertices = ConfigGrid.vertices(squareWidth, squareHeight);
		this.instances = ConfigGrid.instances(squareWidth, squareHeight, row, instance);
		this.colors = ConfigGrid.colors(instance, grid);
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
			final float black = i / (float) total;

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
