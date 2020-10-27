package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Graphics;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import imgui.ImGui;
import imgui.impl.gl.ImplGL3;
import imgui.impl.glfw.ImplGlfw;
import uno.glfw.GlfwWindow;
import imgui.classes.Context;
import org.lwjgl.opengl.GL;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
        private ImplGlfw lwjglGlfw;
        private ImplGL3 implgl3;
        private ImGui imgui = ImGui.INSTANCE;
        private Context ctx;
        private GlfwWindow window;
        long windowHandle;
        
	@Override
	public void create () {
            batch = new SpriteBatch();
            
            //imgui stuff
            GL.createCapabilities();
            ctx=new Context();
            windowHandle =((Lwjgl3Graphics) Gdx.graphics).getWindow().getWindowHandle();
            window=GlfwWindow.from(windowHandle);
            window.makeContextCurrent();
            lwjglGlfw=new ImplGlfw(window,true,null);
            implgl3=new ImplGL3();
            imgui.styleColorsDark(null);
	}

	@Override
	public void render () {
            //render
            Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

            lwjglGlfw.newFrame();

            imgui.text("hello,libgdx");

            imgui.render();

            if (imgui.getDrawData() != null) {
                implgl3.renderDrawData(imgui.getDrawData());
            }
	}
	
	@Override
	public void dispose () {
            batch.dispose();
            lwjglGlfw.shutdown();
            ctx.shutdown();
	}
}
