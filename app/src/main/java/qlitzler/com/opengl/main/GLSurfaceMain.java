package qlitzler.com.opengl.main;

import android.content.Context;
import android.opengl.GLSurfaceView;

import qlitzler.com.opengl.opengl.object.ConfigGrid;

/**
 * Created by qlitzler on 29/05/16.
 */
class GLSurfaceMain extends GLSurfaceView {

	public GLSurfaceMain(Context context, ConfigGrid configGrid) {
		super(context);
		setEGLContextClientVersion(3);
		setRenderer(new GLRendererMain(configGrid));
		setRenderMode(RENDERMODE_WHEN_DIRTY);
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
