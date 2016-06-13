package qlitzler.com.opengl.grid;

import android.content.Context;
import android.opengl.GLSurfaceView;

import qlitzler.com.opengl.opengl.object.Grid;

/**
 * Created by qlitzler on 29/05/16.
 */
class GLSurface extends GLSurfaceView {

	private Grid grid;

	public GLSurface(Context context, Grid grid) {
		super(context);
		this.grid = grid;
		setEGLContextClientVersion(3);
		setRenderer(new GLRenderer(grid));
		setRenderMode(RENDERMODE_WHEN_DIRTY);
	}

	public Grid getGrid() {
		return grid;
	}

	//	int step = 100;
//	float zoom = 10f;
//
//	if (val) {
//		Observable
//			.interval(16666, TimeUnit.MICROSECONDS)
//			.take(step)
//			.observeOn(AndroidSchedulers.mainThread())
//			.subscribe(
//				i -> {
//					float thing = zoom - (zoom - 1) / step * (i + 1);
//					renderer.zoom(thing, step, 1f);
//					requestRender();
//				},
//				Throwable::printStackTrace
//			);
//	} else {
//		Observable
//			.interval(16666, TimeUnit.MICROSECONDS)
//			.take(step)
//			.observeOn(AndroidSchedulers.mainThread())
//			.subscribe(
//				i -> {
//					float thing = 1 + (zoom - 1) / step * (i + 1);
//					renderer.zoom(thing, step, -1f);
//					requestRender();
//				},
//				Throwable::printStackTrace
//			);
//	}
//	val = !val;
}
