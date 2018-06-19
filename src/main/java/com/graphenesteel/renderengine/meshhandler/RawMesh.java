package com.graphenesteel.renderengine.meshhandler;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

public class RawMesh {

    private int vao;
    private List<Integer> vbos = new ArrayList<>();

    public RawMesh(){
        vao = createVAO();
        unbindVAO();
    }
    public RawMesh(float[] positions){
        vao = createVAO();
        storeDataInAttributeList(0, positions);
        unbindVAO();
    }
    private int createVAO() {
        int vaoID = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoID);
        return vaoID;
    }
    private void unbindVAO() {
        GL30.glBindVertexArray(0);
    }

    public int getVAOID(){
        return vao;
    }

    private void storeDataInAttributeList(int attributeNumber, float[] data) {
        int vboID = GL15.glGenBuffers();
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber, 3, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    public void destroy(){
                GL30.glDeleteVertexArrays(vao);
        for (int vbo : vbos) {
            GL15.glDeleteBuffers(vbo);
        }
    }
    private FloatBuffer storeDataInFloatBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
}
