package qlitzler.com.opengl.grid;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;

import java.util.concurrent.TimeUnit;

import qlitzler.com.opengl.AppOpenGL;
import qlitzler.com.opengl.ColorUtils;
import qlitzler.com.opengl.R;
import rx.subjects.PublishSubject;

/**
 * Created by qlitzler on 13/06/16.
 */

public class ActivityTile extends AppCompatActivity {

	public static final String POSITION = "direction";

	private static final ColorUtils colorUtils = AppOpenGL.getColorUtils();

	private static final int SWIPE_DURATION = 300;
	private static final int LEFT = 0;
	private static final int TOP = 1;
	private static final int RIGHT = 2;
	private static final int BOTTOM = 3;

	private View viewStart;
	private FrameLayout viewEnd;
	private GestureDetectorCompat gesture;
	private PublishSubject<Fling> publishFling;
	private int position;
	private int startColor;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tile);

		viewStart = findViewById(R.id.start);
		viewEnd = (FrameLayout) findViewById(R.id.end);
		viewEnd.setBackgroundColor(colorUtils.blue);

		position = getIntent().getIntExtra(POSITION, 0);
		startColor = colorUtils.getColor(AppOpenGL.getMap().bytes[position]);
		gesture = new GestureDetectorCompat(this, new FlingListener());
		publishFling = PublishSubject.create();

		publishFling
			.throttleFirst(SWIPE_DURATION, TimeUnit.MILLISECONDS)
			.subscribe(this::move);
		viewStart.setBackgroundColor(colorUtils.getColor(AppOpenGL.getMap().bytes[position]));
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		gesture.onTouchEvent(event);
		return super.onTouchEvent(event);
	}

	private void move(Fling fling) {
		int newPosition = getNewPosition(position, fling.direction, AppOpenGL.getMap().row);
		animate(fling, newPosition);
	}

	private void animate(Fling fling, int newPosition) {
		int colorBegin = colorUtils.getColor(AppOpenGL.getMap().bytes[position]);
		int colorEnd = colorUtils.getColor(AppOpenGL.getMap().bytes[newPosition]);
		viewEnd.setBackgroundColor(colorEnd);
		position = newPosition;

		ObjectAnimator flingAnimator = ObjectAnimator.ofFloat(viewStart, fling.axis, fling.value);
		AnimatorSet flingSet = new AnimatorSet();
		flingSet.setInterpolator(new AccelerateDecelerateInterpolator());
		flingSet.setDuration(SWIPE_DURATION);
		flingSet.play(flingAnimator);
		flingSet.addListener(new SwipAnimationListener(fling, newPosition, colorBegin, colorEnd));
		flingSet.start();
	}

	private int getNewPosition(int position, int direction, int row) {
		switch (direction) {
			case LEFT:
				return position - 1;
			case RIGHT:
				return position + 1;
			case TOP:
				return position - row;
			case BOTTOM:
				return position + row;
		}
		return -1;
	}

	private class SwipAnimationListener implements Animator.AnimatorListener {

		final Fling fling;
		final int colorBegin;
		final int colorEnd;
		final int newPosition;

		private SwipAnimationListener(Fling fling, int newPosition, int colorBegin, int colorEnd) {
			this.fling = fling;
			this.colorBegin = colorBegin;
			this.colorEnd = colorEnd;
			this.newPosition = newPosition;
		}

		@Override
		public void onAnimationStart(Animator animation) {

		}

		@Override
		public void onAnimationEnd(Animator animation) {
			ObjectAnimator reverseAnimator = ObjectAnimator.ofFloat(viewStart, fling.axis, 0);
			AnimatorSet reverseSet = new AnimatorSet();
			viewStart.setBackgroundColor(colorEnd);
			viewEnd.setBackgroundColor(colorBegin);
			reverseAnimator.setDuration(0);
			reverseSet.play(reverseAnimator);
			reverseSet.start();

			if ((position % AppOpenGL.getMap().row == 0 && fling.direction == LEFT)
				|| (position % AppOpenGL.getMap().row == AppOpenGL.getMap().row - 1 && fling.direction == RIGHT)
				|| (position < AppOpenGL.getMap().row && fling.direction == TOP)
				|| (position >= AppOpenGL.getMap().size - AppOpenGL.getMap().row && fling.direction == BOTTOM)
				|| AppOpenGL.getMap().bytes[newPosition] == 0) {
				viewStart.setBackgroundColor(startColor);
				finishAfterTransition();

//				ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(ActivityTile.this, viewStart, getString(R.string.transition));
//				Intent intent = new Intent(ActivityTile.this, ActivityGrid.class);
//				intent.putExtra(ActivityGrid.POSITION, position);
//				startActivity(intent, options.toBundle());
			}
		}

		@Override
		public void onAnimationCancel(Animator animation) {

		}

		@Override
		public void onAnimationRepeat(Animator animation) {

		}
	}

	private class FlingListener extends GestureDetector.SimpleOnGestureListener {

		private static final String X = "x";
		private static final String Y = "y";

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			final float deltaX = e1.getX() - e2.getX();
			final float deltaY = e1.getY() - e2.getY();
			final Fling fling;

			if (Math.abs(deltaX) < Math.abs(deltaY)) {
				if (deltaY < 0) { // Top to bottom
					fling = new Fling(Y, viewStart.getHeight(), TOP);
				} else { // Bottom to top
					fling = new Fling(Y, -viewStart.getHeight(), BOTTOM);
				}
			} else {
				if (deltaX < 0) { // Left to Right
					fling = new Fling(X, viewStart.getWidth(), LEFT);
				} else { // Right to left
					fling = new Fling(X, -viewStart.getWidth(), RIGHT);
				}
			}
			publishFling.onNext(fling);
			return true;
		}
	}

	@Override
	public void onBackPressed() {
	}
}