package com.graphenesteel.renderengine;

import com.graphenesteel.renderengine.meshhandler.RawMesh;
import com.graphenesteel.renderengine.texture.MeshTexture;

public class TexturedModelResource {

    private RawMesh mesh;
    private MeshTexture texture;


    public TexturedModelResource(RawMesh mesh, MeshTexture texture) {
        this.mesh = mesh;
        this.texture = texture;
    }

    public RawMesh getMesh() {
        return mesh;
    }

    public MeshTexture getTexture() {
        return texture;
    }
}
