package com.graphenesteel.renderengine.meshhandler;

import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import org.lwjgl.opengl.GL;


import static org.junit.jupiter.api.Assertions.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

class RawMeshTest {



    @Test
    public void shouldStoreVAOId(){
        RawMesh mesh = new RawMesh();
        assertNotEquals(0,mesh.getVAOID());
        mesh.destroy();
    }

    @Test
    public void shouldStoreMeshFromGivenPoints(){
        float[] vertices = { -0.5f, 0.5f, 0f,
                            -0.5f, -0.5f, 0f,
                            0.5f, -0.5f, 0f,
                            0.5f, -0.5f, 0f,
                            0.5f, 0.5f, 0f,
                            -0.5f, 0.5f, 0f };
        RawMesh mesh = new RawMesh(vertices);
        assertNotEquals(0,mesh.getVAOID());
        assertNotEquals(0,mesh.getVAOID());
        mesh.destroy();
    }

    @BeforeAll
    private static void createContext(){
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");
        long window = glfwCreateWindow(10, 10, "Hello World!", NULL, NULL);
        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        GL.createCapabilities();
    }

}