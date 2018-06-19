package com.graphenesteel.renderengine.meshhandler;

import com.graphenesteel.renderengine.WindowManager;
import org.junit.jupiter.api.Test;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import static org.junit.jupiter.api.Assertions.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

class RawMeshTest {



    @Test
    public void shouldStoreVAOId(){
        createContext();
        RawMesh mesh = new RawMesh();
        assertNotEquals(0,mesh.getVAOID());
        mesh.destroy();
    }

    @Test
    public void shouldStoreMeshFromGivenPoints(){
        createContext();
        float[] vertices = { -0.5f, 0.5f, 0f,
                            -0.5f, -0.5f, 0f,
                            0.5f, -0.5f, 0f,
                            0.5f, -0.5f, 0f,
                            0.5f, 0.5f, 0f,
                            -0.5f, 0.5f, 0f };
        RawMesh mesh = new RawMesh(vertices);
        assertNotEquals(0,mesh.getVAOID());
        mesh.destroy();
    }


    private void createContext(){
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");
        long window = glfwCreateWindow(10, 10, "Hello World!", NULL, NULL);
        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        GL.createCapabilities();


    }

}