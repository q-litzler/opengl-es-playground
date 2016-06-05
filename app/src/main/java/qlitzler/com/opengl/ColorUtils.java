package qlitzler.com.opengl;

import android.content.Context;
import android.support.v4.content.ContextCompat;

/**
 * Created by qlitzler on 29/05/16.
 */
public class ColorUtils {

	public final int aero;
	public final int pink;
	public final int brown;
	public final int yellow;
	public final int turquoise;
	public final int orange;
	public final int purple;
	public final int red;
	public final int green;
	public final int blue;

	public ColorUtils(Context context) {
		aero = ContextCompat.getColor(context, R.color.aero);
		pink = ContextCompat.getColor(context, R.color.pink);
		brown = ContextCompat.getColor(context, R.color.brown);
		yellow = ContextCompat.getColor(context, R.color.yellow);
		turquoise = ContextCompat.getColor(context, R.color.turquoise);
		orange = ContextCompat.getColor(context, R.color.orange);
		purple = ContextCompat.getColor(context, R.color.purple);
		red = ContextCompat.getColor(context, R.color.red);
		green = ContextCompat.getColor(context, R.color.green);
		blue = ContextCompat.getColor(context, R.color.blue);
	}

	public int getColor(byte code) {
		switch (code) {
			case 0:
				return blue;
			case 1:
				return yellow;
			case 2:
				return pink;
			case 3:
				return purple;
			case 4:
				return turquoise;
			case 5:
				return blue;
			case 's':
				return yellow;
			case 'e':
				return yellow;
		}
		return 0;
	}
}
