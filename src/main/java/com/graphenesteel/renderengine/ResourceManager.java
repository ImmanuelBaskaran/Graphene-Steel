package com.graphenesteel.renderengine;

import com.graphenesteel.renderengine.meshhandler.MeshFactory;
import com.graphenesteel.renderengine.meshhandler.RawMesh;
import com.graphenesteel.renderengine.texture.MeshTexture;
import com.graphenesteel.renderengine.texture.TextureFactory;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class ResourceManager {


    private List<TexturedModelResource> resources = new ArrayList<>();


    public ResourceManager(){

    }
    public TexturedModelResource loadResource(String fileName){
        RawMesh mesh = MeshFactory.load(fileName);
        MeshTexture texture = new MeshTexture(TextureFactory.loadTexture("stone"));
        TexturedModelResource res = new TexturedModelResource(mesh,texture);
        resources.add(res);
        return res;
    }


    public void cleanUp(){
        for(TexturedModelResource res:resources){
            res.getMesh().destroy();
            GL11.glDeleteTextures(res.getTexture().getTextureID());
        }
    }



}
