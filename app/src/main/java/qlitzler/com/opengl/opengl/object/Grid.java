package qlitzler.com.opengl.opengl.object;

import qlitzler.com.opengl.grid.Map;

/**
 * Created by qlitzler on 04/06/16.
 */

public class Grid {

	public final Map map;
	public final float squareWidth;
	public final float squareHeight;

	public static Grid newInstance(Map map, int width, int height) {
		return new Grid(
			map,
			width / (float) map.row,
			height / (float) map.row
		);
	}

	public int getPosition(float x, float y) {
		final int posX = (int) x / (int) squareWidth;
		final int posY = (int) y / (int) squareHeight;

		return (posX + posY * map.row);
	}

	public int getX(int position) {
		return (position % map.row) * (int) squareWidth;
	}

	public int getY(int position) {
		return (position / map.row) * (int) squareHeight;
	}

	private Grid(Map map, float squareWidth, float squareHeight) {
		this.map = map;
		this.squareWidth = squareWidth;
		this.squareHeight = squareHeight;
	}
}
