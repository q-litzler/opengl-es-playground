package qlitzler.com.opengl;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by qlitzler on 29/05/16.
 */
public class AppOpenGL extends android.app.Application {

	public static byte[] map = {
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

	private static AppOpenGL instance;
	private ColorUtils colorUtils;

	public static WindowManager windowManager;
	public static DisplayMetrics displayMetrics;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		colorUtils = new ColorUtils(this);
		windowManager = (WindowManager) instance.getSystemService(Context.WINDOW_SERVICE);
		displayMetrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(displayMetrics);
	}

	public static ColorUtils getColorUtils() {
		return instance.colorUtils;
	}

	public static DisplayMetrics getDisplayMetrics() {
		return displayMetrics;
	}

}
