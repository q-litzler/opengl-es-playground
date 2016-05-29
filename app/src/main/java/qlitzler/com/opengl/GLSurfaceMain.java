package qlitzler.com.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by qlitzler on 29/05/16.
 */
class GLSurfaceMain extends GLSurfaceView {

	private final GLRendererMain renderer;

	public GLSurfaceMain(Context context) {
		super(context);

		setEGLContextClientVersion(3);
		renderer = new GLRendererMain();
		setRenderer(renderer);
		setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}
}
