package qlitzler.com.opengl.opengl.object;

import static qlitzler.com.opengl.opengl.object.GLObject.RGBA;
import static qlitzler.com.opengl.opengl.object.GLObject.XY;

/**
 * Created by qlitzler on 04/06/16.
 */

public class ConfigGrid {

	final float[] vertices;
	final float[] colors;
	final float[] instances;
	final short[] indices;

	public final int instance;

	public static ConfigGrid newInstance(float[] vertices, float[] colors, float[] instances, int instance) {
		return new ConfigGrid(vertices, colors, instances, ConfigGrid.ELEMENTS, instance);
	}

	private ConfigGrid(float[] vertices, final float[] colors, final float[] instances, final short[] indices, int instance) {
		this.vertices = vertices;
		this.instances = instances;
		this.colors = colors;
		this.indices = indices;
		this.instance = instance;
	}

	public static float[] vertices(float x, float y) {
		return new float[]{
			-x, y,
			-x, -y,
			x, -y,
			x, y,
		};
	}

	public static float[] colors(int total) {
		float[] position = new float[total * RGBA];

		for (int i = 0; i < total; ++i) {
			position[i * RGBA] = i / (float)total;
			position[i * RGBA + 1] = i / (float)total;
			position[i * RGBA + 2] = i / (float)total;
			position[i * RGBA + 3] = 0.0f;
		}
		return position;
	}

	public static float[] instances(float x, float y, int row, int total) {
		float[] position = new float[total * XY];
		int indexX = 1;
		int indexY = 1;

		for (int i = 0; i < total; ++i) {
			if (i != 0 && i % row == 0) {
				indexX = 1;
				++indexY;
			}
			position[i * XY] = 0;
			position[i * XY + 1] = 0;
			++indexX;
		}
		return position;
	}

//	private static final float COORDS1[] = {
//		-0.2f, 0.2f, 0.0f,
//		-0.2f, -0.2f, 0.0f,
//		0.2f, -0.2f, 0.0f,
//		0.2f, 0.2f, 0.0f,
//	};


//	private static final float[] BLACK = {
//		0.0f, 0.0f, 0.5f, 0.0f,
//		0.5f, 0.0f, 0.0f, 0.0f,
//		0.0f, 0.5f, 0.0f, 0.0f,
//		0.5f, 0.5f, 0.5f, 0.0f,
//	};

	private static final short ELEMENTS[] = {0, 1, 2, 0, 2, 3};
}
