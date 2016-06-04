package qlitzler.com.opengl.shader;

import android.opengl.GLES30;

import qlitzler.com.opengl.GLRendererMain;

/**
 * Created by qlitzler on 04/06/16.
 */

public abstract class Shader {

	public final int program;

	Shader() {
		this.program = shader();
	}

	protected abstract String getShaderVertex();

	protected abstract String getShaderFragment();

	private int shader() {
		final int vertex = GLRendererMain.loadShader(GLES30.GL_VERTEX_SHADER, getShaderVertex());
		final int fragment = GLRendererMain.loadShader(GLES30.GL_FRAGMENT_SHADER, getShaderFragment());
		final int program = GLES30.glCreateProgram();

		GLES30.glAttachShader(program, vertex);
		GLES30.glAttachShader(program, fragment);
		GLES30.glLinkProgram(program);
		return program;
	}
}
