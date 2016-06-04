package qlitzler.com.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by qlitzler on 29/05/16.
 */
class GLSurfaceMain extends GLSurfaceView {

	public GLSurfaceMain(Context context) {
		super(context);

		setEGLContextClientVersion(3);
		setRenderer(new GLRendererMain());
		setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}
}
