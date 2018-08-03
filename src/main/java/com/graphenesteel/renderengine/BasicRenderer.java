package com.graphenesteel.renderengine;

import com.graphenesteel.renderengine.entities.Camera;
import com.graphenesteel.renderengine.entities.Entity;
import com.graphenesteel.renderengine.lightning.Light;
import com.graphenesteel.renderengine.maths.MatrixUtils;
import com.graphenesteel.renderengine.meshhandler.RawMesh;

import com.graphenesteel.renderengine.shaders.ForwardDirectional;
import com.graphenesteel.renderengine.shaders.PhongShader;
import com.graphenesteel.renderengine.shaders.Shader;
import com.graphenesteel.renderengine.shaders.ShaderImpl;
import com.graphenesteel.renderengine.texture.MeshTexture;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import java.util.List;
import java.util.Map;

public class BasicRenderer{

    private PhongShader shader;
    private ForwardDirectional s2 = new ForwardDirectional();
    private List<Shader> passes;
    Light light = new Light(new Vector3f(0,10,0),new Vector3f(1,1,1),1);

    public BasicRenderer(PhongShader shader,Matrix4f projectionMatrix) {
        this.shader = shader;
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.loadLights();
        shader.stop();
        s2.start();
        s2.loadLight(light);
        s2.loadProjectionMatrix(projectionMatrix);
        s2.stop();
    }
    public void renderAllPasses(Map<TexturedModelResource, List<Entity>> entities, Camera camera){
        render(entities,camera,this.shader);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_ONE,GL11.GL_ONE);
        GL11.glDepthMask(false);
        GL11.glDepthFunc(GL11.GL_EQUAL);
            render(entities, camera, this.s2);
        GL11.glDepthFunc(GL11.GL_LESS);
        GL11.glDepthMask(true);
    }

    public void render(Map<TexturedModelResource, List<Entity>> entities, Camera camera,Shader shader) {
        shader.start();
        shader.loadView(camera);
        for (TexturedModelResource model : entities.keySet()) {
            prepareTexturedModel(model);
            List<Entity> batch = entities.get(model);
            for (Entity entity : batch) {
                prepareInstance(entity);
                GL11.glDrawElements(GL11.GL_TRIANGLES, model.getMesh().getVertexCount(),
                        GL11.GL_UNSIGNED_INT, 0);
            }
            unbindTexturedModel();
        }
        shader.stop();
    }

    private void prepareTexturedModel(TexturedModelResource model) {
        RawMesh rawModel = model.getMesh();
        GL30.glBindVertexArray(rawModel.getVAOID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);


        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getTextureID());
    }

    private void unbindTexturedModel() {
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }

    private void prepareInstance(Entity entity) {
        Matrix4f transformationMatrix = MatrixUtils.createTransformationMatrix(entity.getPosition(),
                entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
        shader.loadTransformationMatrix(transformationMatrix);
    }
}
