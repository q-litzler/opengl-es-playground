package qlitzler.com.opengl.main;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import qlitzler.com.opengl.R;

/**
 * Created by qlitzler on 29/05/16.
 */
public class ActivityMain extends AppCompatActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		GLSurfaceView surface = new GLSurfaceMain(this);
		setContentView(surface);
	}
}