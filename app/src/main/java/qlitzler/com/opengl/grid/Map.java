package qlitzler.com.opengl.grid;

/**
 * Created by qlitzler on 13/06/16.
 */

public class Map {

	public static byte[] MAP = {
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		0, 1, 2, 3, 4, 5, 1, 2, 3, 0,
		0, 5, 0, 0, 0, 0, 0, 0, 4, 0,
		0, 4, 0, 0, 0, 0, 0, 0, 5, 0,
		0, 3, 0, 0, 0, 0, 0, 0, 1, 0,
		0, 2, 1, 's', 0, 0, 0, 0, 2, 0,
		0, 0, 0, 0, 0, 'e', 5, 4, 3, 0,
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0
	};

	public final byte[] bytes;
	public final int size;
	public final int row;
	public boolean valid;

	public Map(byte[] bytes) {
		this.bytes = bytes;
		this.size = bytes.length;
		this.row = (int) Math.sqrt(size);
		this.valid = Math.sqrt(size) % 1 == 0;
	}
}
