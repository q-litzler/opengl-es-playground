package qlitzler.com.opengl;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import qlitzler.com.opengl.grid.Map;

/**
 * Created by qlitzler on 29/05/16.
 */
public class AppOpenGL extends android.app.Application {

	private static AppOpenGL instance;

	public static WindowManager windowManager;
	public static DisplayMetrics displayMetrics;

	private ColorUtils colorUtils;
	private Map map;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		colorUtils = new ColorUtils(this);
		windowManager = (WindowManager) instance.getSystemService(Context.WINDOW_SERVICE);
		displayMetrics = new DisplayMetrics();
		windowManager.getDefaultDisplay().getMetrics(displayMetrics);
		map = new Map(Map.MAP);
	}

	public static Map getMap() {
		return instance.map;
	}

	public static ColorUtils getColorUtils() {
		return instance.colorUtils;
	}

	public static DisplayMetrics getDisplayMetrics() {
		return displayMetrics;
	}

}
