package com.graphenesteel.renderengine;

import com.graphenesteel.renderengine.meshhandler.RawMesh;

import org.junit.jupiter.api.Test;


class RendererTest {


    @Test
    //integration test
    void shouldRenderASquare(){
        WindowManager window = new WindowManager(400,400);
        Renderer renderer = new BasicRenderer();
        float[] vertices = { -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f,
                -0.5f, 0.5f, 0f };
        RawMesh mesh = new RawMesh(vertices,6);
        while (!window.isCloseRequested()) {
            window.update();

            renderer.render(mesh);
        }
        mesh.destroy();
        window.destroy();
    }

}