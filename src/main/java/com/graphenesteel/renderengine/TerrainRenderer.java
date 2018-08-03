package com.graphenesteel.renderengine;

import com.graphenesteel.renderengine.entities.Camera;
import com.graphenesteel.renderengine.entities.Entity;
import com.graphenesteel.renderengine.lightning.Light;
import com.graphenesteel.renderengine.maths.MatrixUtils;
import com.graphenesteel.renderengine.meshhandler.RawMesh;
import com.graphenesteel.renderengine.shaders.ForwardDirectional;
import com.graphenesteel.renderengine.shaders.Shader;
import com.graphenesteel.renderengine.shaders.ShaderImpl;
import com.graphenesteel.renderengine.shaders.TerrainShaderImpl;
import com.graphenesteel.renderengine.terrain.Terrain;
import com.graphenesteel.renderengine.texture.MeshTexture;
import com.graphenesteel.renderengine.texture.TerrainTexturePack;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import java.util.List;
import java.util.Map;

public class TerrainRenderer {


        private TerrainShaderImpl shader;
    private ForwardDirectional s2 = new ForwardDirectional();
    private List<Shader> passes;
    Light light = new Light(new Vector3f(0,100,0),new Vector3f(1,1,1),1);

        public TerrainRenderer(TerrainShaderImpl shader, Matrix4f projectionMatrix) {
            this.shader = shader;
            shader.start();
            shader.loadProjectionMatrix(projectionMatrix);
            shader.ConnectTextureUnits();
            shader.stop();
            s2.start();
            s2.loadLight(light);
            s2.loadProjectionMatrix(projectionMatrix);
            s2.stop();
        }
        public void renderAllPasses(List<Terrain> terrains, Camera camera){
            render(terrains,camera,this.shader);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_ONE,GL11.GL_ONE);
            GL11.glDepthMask(false);
            GL11.glDepthFunc(GL11.GL_EQUAL);

                render(terrains, camera, this.s2);

            GL11.glDepthFunc(GL11.GL_LESS);
            GL11.glDepthMask(true);



        }

        public void render(List<Terrain> terrains, Camera camera,Shader shader) {

            shader.start();
            shader.loadView(camera);
            for (Terrain terrain : terrains) {
                prepareTerrain(terrain);
                loadModelMatrix(terrain);
                GL11.glDrawElements(GL11.GL_TRIANGLES, terrain.getModel().getVertexCount(),
                        GL11.GL_UNSIGNED_INT, 0);
                unbindTexturedModel();
            }
            shader.stop();
        }

        private void prepareTerrain(Terrain terrain) {
            RawMesh rawModel = terrain.getModel();
            GL30.glBindVertexArray(rawModel.getVAOID());
            GL20.glEnableVertexAttribArray(0);
            GL20.glEnableVertexAttribArray(1);
            GL20.glEnableVertexAttribArray(2);

            bindTextures(terrain);

        }

        private void bindTextures(Terrain t){
            TerrainTexturePack pack = t.getTexture();

            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, pack.getBackground().getTextureID());
            GL13.glActiveTexture(GL13.GL_TEXTURE1);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, pack.getR().getTextureID());
            GL13.glActiveTexture(GL13.GL_TEXTURE2);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, pack.getG().getTextureID());
            GL13.glActiveTexture(GL13.GL_TEXTURE3);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, pack.getB().getTextureID());
            GL13.glActiveTexture(GL13.GL_TEXTURE4);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, pack.getBlendMap().getTextureID());
        }
        private void unbindTexturedModel() {
            GL20.glDisableVertexAttribArray(0);
            GL20.glDisableVertexAttribArray(1);
            GL20.glDisableVertexAttribArray(2);
            GL30.glBindVertexArray(0);
        }

        private void loadModelMatrix(Terrain terrain) {
            Matrix4f transformationMatrix = MatrixUtils.createTransformationMatrix(
                    new Vector3f(terrain.getX(), 0, terrain.getZ()), 0, 0, 0, 1);
            shader.loadTransformationMatrix(transformationMatrix);
        }

}
