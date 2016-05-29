package qlitzler.com.opengl;

import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;

/**
 * Created by qlitzler on 29/05/16.
 */
class GLContext implements GLSurfaceView.EGLContextFactory {

	private static final int EGL_CONTEXT_CLIENT_VERSION = 0x3098;
	private static final double GL_VERSION = 3.0;

	@Override
	public EGLContext createContext(EGL10 egl, EGLDisplay display, EGLConfig eglConfig) {
		int[] attributes = {EGL_CONTEXT_CLIENT_VERSION, (int) GL_VERSION, EGL10.EGL_NONE};

		return egl.eglCreateContext(display, eglConfig, EGL10.EGL_NO_CONTEXT, attributes);
	}

	@Override
	public void destroyContext(EGL10 egl, EGLDisplay display, EGLContext context) {

	}
}
