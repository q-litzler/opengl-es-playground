package qlitzler.com.opengl.opengl.shader;

import android.opengl.GLES20;
import android.opengl.GLES30;

/**
 * Created by qlitzler on 04/06/16.
 */

public class ShaderGrid extends Shader {

	public final int position;
	public final int color;
	public final int instance;
	public final int mvp;

	public ShaderGrid() {
		position = GLES30.glGetAttribLocation(program, "v_position");
		color = GLES30.glGetAttribLocation(program, "v_color");
		instance = GLES30.glGetAttribLocation(program, "v_instance");
		mvp = GLES20.glGetUniformLocation(program, "matrix_mvp");
	}

	@Override
	protected String getShaderVertex() {
		return " #version 300 es\n" +
			"uniform mat4 matrix_mvp;" +
			"in vec4 v_position;" +
			"in vec4 v_color;" +
			"in vec4 v_instance;" +
			"out vec4 f_color;" +
			"void main() {" +
			"	gl_Position = matrix_mvp * (v_position + v_instance);" +
			"	f_color = v_color;" +
			"}";
	}

	@Override
	protected String getShaderFragment() {
		return " #version 300 es\n" +
			"in vec4 f_color;" +
			"out vec4 outColor;" +
			"void main() {" +
			"  outColor = f_color;" +
			"}";
	}
}