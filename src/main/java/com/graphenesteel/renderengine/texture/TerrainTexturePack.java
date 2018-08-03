package com.graphenesteel.renderengine.texture;

public class TerrainTexturePack {

    private TerrainTexture background;
    private TerrainTexture r;
    private TerrainTexture g;
    private TerrainTexture b;
    private TerrainTexture blendMap;


    public TerrainTexturePack(TerrainTexture background, TerrainTexture r, TerrainTexture g, TerrainTexture b, TerrainTexture blendMap) {
        this.background = background;
        this.r = r;
        this.g = g;
        this.b = b;
        this.blendMap = blendMap;
    }

    public TerrainTexture getBackground() {
        return background;
    }

    public TerrainTexture getR() {
        return r;
    }

    public TerrainTexture getG() {
        return g;
    }

    public TerrainTexture getB() {
        return b;
    }

    public TerrainTexture getBlendMap() {
        return blendMap;
    }
}
