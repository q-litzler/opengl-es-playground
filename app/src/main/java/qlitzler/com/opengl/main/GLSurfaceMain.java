package qlitzler.com.opengl.main;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import qlitzler.com.opengl.AppOpenGL;
import qlitzler.com.opengl.opengl.object.ConfigGrid;

/**
 * Created by qlitzler on 29/05/16.
 */
class GLSurfaceMain extends GLSurfaceView implements View.OnTouchListener {

	public GLSurfaceMain(Context context) {
		super(context);

		DisplayMetrics displayMetrics = AppOpenGL.getDisplayMetrics();

		int total = AppOpenGL.map.length;

		boolean isPerfectSquare = Math.sqrt(total) % 1 == 0;

		setOnTouchListener(this);
		setEGLContextClientVersion(3);
		setRenderer(
			new GLRendererMain(
				ConfigGrid.newInstance(
					AppOpenGL.map,
					total,
					displayMetrics.widthPixels,
					displayMetrics.heightPixels
				)
			)
		);
		setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return false;
	}
}
