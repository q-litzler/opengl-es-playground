package qlitzler.com.opengl;

/**
 * Created by qlitzler on 29/05/16.
 */
public class AppOpenGL extends android.app.Application {

	byte[] map = {
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		3, 3, 3, 3, 3, 3, 3, 3, 3, 4,
		2, 0, 0, 0, 0, 0, 0, 0, 0, 4,
		2, 0, 0, 0, 0, 0, 0, 0, 0, 4,
		2, 0, 0, 0, 0, 0, 0, 0, 0, 4,
		2, 1, 1, 'x', 0, 0, 0, 0, 0, 4,
		0, 0, 0, 0, 0, 'z', 5, 5, 5, 5,
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0
	};

	private static AppOpenGL instance;
	private ColorUtils colorUtils;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		colorUtils = new ColorUtils(this);
	}

	public static ColorUtils getColorUtils() {
		return instance.colorUtils;
	}
}
