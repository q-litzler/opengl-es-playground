package qlitzler.com.opengl;

/**
 * Created by qlitzler on 29/05/16.
 */
public class AppOpenGL extends android.app.Application {

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
