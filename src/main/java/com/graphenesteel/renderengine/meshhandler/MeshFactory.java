package com.graphenesteel.renderengine.meshhandler;

public class MeshFactory {

    private static MeshLoader loader = new OBJMeshLoader();


    public static RawMesh load(String file){
        return loader.load(file);
    }

}
