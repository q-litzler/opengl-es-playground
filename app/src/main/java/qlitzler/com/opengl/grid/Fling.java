package qlitzler.com.opengl.grid;

/**
 * Created by qlitzler on 13/06/16.
 */

public class Fling {

	public final String axis;
	public final int value;
	public final int direction;

	public Fling(String axis, int value, int direction) {
		this.axis = axis;
		this.value = value;
		this.direction = direction;
	}
}
