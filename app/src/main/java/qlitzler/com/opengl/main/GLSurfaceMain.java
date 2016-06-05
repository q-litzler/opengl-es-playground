package qlitzler.com.opengl.main;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.DisplayMetrics;

import qlitzler.com.opengl.AppOpenGL;
import qlitzler.com.opengl.opengl.object.ConfigGrid;

/**
 * Created by qlitzler on 29/05/16.
 */
class GLSurfaceMain extends GLSurfaceView {

	public GLSurfaceMain(Context context) {
		super(context);

		DisplayMetrics displayMetrics = AppOpenGL.getDisplayMetrics();
		final int width = displayMetrics.widthPixels;
		final int height = displayMetrics.heightPixels;

		System.out.println(width + " " + height);

		int grid = 4;
		double square = Math.sqrt(grid);

		boolean isPerfectSquare = square % 1 == 0;

		final int row = (int) square;

		System.out.println(row + " " + isPerfectSquare);

		float squareWidth = width / (float) grid;
		float squareHeight = height / (float) grid;

		System.out.println(squareWidth + " " + squareHeight);

		setEGLContextClientVersion(3);
		setRenderer(new GLRendererMain(ConfigGrid.vertices(squareWidth, squareHeight), ConfigGrid.colors(grid), ConfigGrid.instances(squareWidth, squareHeight, row, grid), grid));
		setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}
}
