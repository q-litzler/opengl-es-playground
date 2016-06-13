package qlitzler.com.opengl.grid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import qlitzler.com.opengl.AppOpenGL;
import qlitzler.com.opengl.R;
import qlitzler.com.opengl.opengl.object.Grid;

/**
 * Created by qlitzler on 29/05/16.
 */
public class ActivityGrid extends AppCompatActivity implements View.OnTouchListener {

	private ViewGroup main;
	private GLSurface surface;
	private View view;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DisplayMetrics screen = AppOpenGL.getDisplayMetrics();

		final int size = AppOpenGL.map.length;

		boolean isPerfectSquare = Math.sqrt(size) % 1 == 0;

		Grid grid = Grid.newInstance(AppOpenGL.map, size, screen.widthPixels, screen.heightPixels);
		surface = new GLSurface(getBaseContext(), grid);
		setContentView(surface);
		main = (ViewGroup) findViewById(android.R.id.content);
		view = newSquare((int) grid.squareWidth, (int) grid.squareHeight);
		surface.setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (main.getChildCount() == 1) {
			main.addView(view);
		}
		Grid grid = surface.getGrid();
		int position = grid.getPosition(event.getX(), event.getY());
		int color = AppOpenGL.getColorUtils().getColor(grid.grid[position]);

		view.setX(grid.getX(position));
		view.setY(grid.getY(position));
		view.setBackgroundColor(color);
		view.requestLayout();
		return false;
	}

	private View newSquare(int squareWidth, int squareHeight) {
		View view = new View(getBaseContext());
		view.setLayoutParams(new ViewGroup.LayoutParams(squareWidth, squareHeight));
		view.setTransitionName(getString(R.string.transition));
		return view;
	}
}
