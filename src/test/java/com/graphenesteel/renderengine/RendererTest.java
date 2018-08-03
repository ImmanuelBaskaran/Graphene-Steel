package com.graphenesteel.renderengine;

import com.graphenesteel.renderengine.meshhandler.RawMesh;

import org.junit.jupiter.api.Test;


class RendererTest {


    @Test
    //integration test
    void shouldRenderASquare(){
        WindowManager window = new WindowManager(400,400);
        //Renderer renderer = new BasicRenderer();

        float[] vertices = {
                -0.5f, 0.5f, 0f,//v0
                -0.5f, -0.5f, 0f,//v1
                0.5f, -0.5f, 0f,//v2
                0.5f, 0.5f, 0f,//v3
        };
        int[] indices = {

                3,1,2,
                0,1,3,
        };

        RawMesh mesh = new RawMesh(vertices,indices);
        while (!window.isCloseRequested()) {
            window.update();

          //  renderer.render(mesh);
        }
        mesh.destroy();
        window.destroy();
    }

}