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

	public static final String POSITION = "position";

	private static final ColorUtils colorUtils = AppOpenGL.getColorUtils();

	private static final int SWIPE_DURATION = 300;

	private View viewStart;
	private FrameLayout viewEnd;
	private GestureDetectorCompat gesture;
	private PublishSubject<Fling> publishFling;
	private int position;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tile);

		viewStart = findViewById(R.id.start);
		viewEnd = (FrameLayout) findViewById(R.id.end);

		position = getIntent().getIntExtra(POSITION, 0);
		viewStart.setBackgroundColor(colorUtils.getColor(AppOpenGL.getMap().bytes[position]));

		publishFling = PublishSubject.create();
		publishFling
			.throttleFirst(SWIPE_DURATION, TimeUnit.MILLISECONDS)
			.subscribe(this::animate);
		gesture = new GestureDetectorCompat(this, new FlingListener());
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		gesture.onTouchEvent(event);
		return super.onTouchEvent(event);
	}

	private void animate(Fling fling) {
		int colorBegin = colorUtils.getColor(AppOpenGL.getMap().bytes[position]);
		int colorEnd = colorUtils.getColor(AppOpenGL.getMap().bytes[fling.position]);
		viewEnd.setBackgroundColor(colorEnd);
		position = fling.position;

		ObjectAnimator flingAnimator = ObjectAnimator.ofFloat(viewStart, fling.axis, fling.value);
		AnimatorSet flingSet = new AnimatorSet();
		flingSet.setInterpolator(new AccelerateDecelerateInterpolator());
		flingSet.setDuration(SWIPE_DURATION);
		flingSet.play(flingAnimator);
		flingSet.addListener(new SwipAnimationListener(fling.axis, colorBegin, colorEnd));
		flingSet.start();
	}

	public class SwipAnimationListener implements Animator.AnimatorListener {

		final String axis;
		final int colorBegin;
		final int colorEnd;

		public SwipAnimationListener(String axis, int colorBegin, int colorEnd) {
			this.axis = axis;
			this.colorBegin = colorBegin;
			this.colorEnd = colorEnd;
		}

		@Override
		public void onAnimationStart(Animator animation) {

		}

		@Override
		public void onAnimationEnd(Animator animation) {
			ObjectAnimator reverseAnimator = ObjectAnimator.ofFloat(viewStart, axis, 0);
			AnimatorSet reverseSet = new AnimatorSet();
			viewStart.setBackgroundColor(colorEnd);
			viewEnd.setBackgroundColor(colorBegin);
			reverseAnimator.setDuration(0);
			reverseSet.play(reverseAnimator);
			reverseSet.start();
		}

		@Override
		public void onAnimationCancel(Animator animation) {

		}

		@Override
		public void onAnimationRepeat(Animator animation) {

		}
	}

	public class FlingListener extends GestureDetector.SimpleOnGestureListener {

		private static final String X = "x";
		private static final String Y = "y";

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			final float deltaX = e1.getX() - e2.getX();
			final float deltaY = e1.getY() - e2.getY();
			final Fling fling;

			if (Math.abs(deltaX) < Math.abs(deltaY)) {
				if (deltaY < 0) { // Top to bottom
					fling = new Fling(Y, viewStart.getHeight(), position - 10);
				} else { // Bottom to top
					fling = new Fling(Y, -viewStart.getHeight(), position + 10);
				}
			} else {
				if (deltaX < 0) { // Left to Right
					fling = new Fling(X, viewStart.getWidth(), position - 1);
				} else { // Right to left
					fling = new Fling(X, -viewStart.getWidth(), position + 1);
				}
			}
			publishFling.onNext(fling);
			return true;
		}
	}
}