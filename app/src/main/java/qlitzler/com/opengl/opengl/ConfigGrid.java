package qlitzler.com.opengl.opengl;

/**
 * Created by qlitzler on 04/06/16.
 */

public class ConfigGrid {

	final float[] vertices;
	final float[] colors;
	final float[] instances;
	final short[] indices;

	public static ConfigGrid newInstance() {
		return new ConfigGrid(ConfigGrid.COORDS1, ConfigGrid.BLACK, ConfigGrid.POS1, ConfigGrid.ELEMENTS);
	}

	private ConfigGrid(float[] vertices, final float[] colors, final float[] instances, final short[] indices) {
		this.vertices = vertices;
		this.colors = colors;
		this.instances = instances;
		this.indices = indices;
	}

	private static final float COORDS1[] = {
		-0.2f, 0.2f, 0.0f,
		-0.2f, -0.2f, 0.0f,
		0.2f, -0.2f, 0.0f,
		0.2f, 0.2f, 0.0f,
	};

	private static final float POS1[] = {
		0.4f, 0.0f, 0.0f,
		0, 0f, 0.0f, 0.0f,
	};

	private static final float[] BLACK = {
		0.0f, 0.0f, 0.5f, 0.0f,
		0.5f, 0.0f, 0.0f, 0.0f,
		0.0f, 0.5f, 0.0f, 0.0f,
		0.5f, 0.5f, 0.5f, 0.0f,
	};

	private static final short ELEMENTS[] = {0, 1, 2, 0, 2, 3};
}
