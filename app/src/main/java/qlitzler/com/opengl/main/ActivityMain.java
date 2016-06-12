package qlitzler.com.opengl.main;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import qlitzler.com.opengl.AppOpenGL;
import qlitzler.com.opengl.opengl.object.ConfigGrid;

/**
 * Created by qlitzler on 29/05/16.
 */
public class ActivityMain extends AppCompatActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DisplayMetrics displayMetrics = AppOpenGL.getDisplayMetrics();

		final int instance = AppOpenGL.map.length;

		boolean isPerfectSquare = Math.sqrt(instance) % 1 == 0;

		ConfigGrid configGrid = ConfigGrid.newInstance(
			AppOpenGL.map,
			instance,
			displayMetrics.widthPixels,
			displayMetrics.heightPixels
		);
		GLSurfaceView surface = new GLSurfaceMain(getBaseContext(), configGrid);
		setContentView(surface);
	}
}
