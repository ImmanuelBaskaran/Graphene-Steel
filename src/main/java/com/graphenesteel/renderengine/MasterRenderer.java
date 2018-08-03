package com.graphenesteel.renderengine;

import com.graphenesteel.renderengine.entities.Camera;
import com.graphenesteel.renderengine.entities.Entity;

import com.graphenesteel.renderengine.shaders.PhongShader;

import com.graphenesteel.renderengine.shaders.TerrainShaderImpl;
import com.graphenesteel.renderengine.terrain.Terrain;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterRenderer {


    private static final float FOV = 70;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 1000;

    private Matrix4f projectionMatrix;


    private PhongShader shader = new PhongShader();
    private TerrainShaderImpl shadert = new TerrainShaderImpl();



    private BasicRenderer renderer;
    private TerrainRenderer terrainRenderer;

    private Map<TexturedModelResource,List<Entity>> entities = new HashMap<>();
    private List<Terrain> terrains = new ArrayList<Terrain>();

    public MasterRenderer(){
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
        createProjectionMatrix();
        renderer = new BasicRenderer(shader,projectionMatrix);
        terrainRenderer = new TerrainRenderer(shadert,projectionMatrix);
    }

    public void render(Camera camera){
        prepare();

        renderer.renderAllPasses(entities,camera);


        terrainRenderer.renderAllPasses(terrains,camera);

        entities.clear();
        terrains.clear();
    }


    public void processTerrain(Terrain t){
        terrains.add(t);
    }

    public void processEntity(Entity entity){
        TexturedModelResource entityModel = entity.getModel();
        List<Entity> batch = entities.get(entityModel);
        if(batch!=null){
            batch.add(entity);
        }else{
            List<Entity> newBatch = new ArrayList<>();
            newBatch.add(entity);
            entities.put(entityModel, newBatch);
        }
    }

    public void cleanUp(){
        shader.cleanUp();
        shadert.cleanUp();
    }

    public void prepare() {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0,0,0, 1);
    }

    private void createProjectionMatrix() {
        float aspectRatio = (float) 800 / (float) 600;
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustum_length = FAR_PLANE - NEAR_PLANE;

        projectionMatrix = new Matrix4f();
        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
        projectionMatrix.m33 = 0;
    }
}
