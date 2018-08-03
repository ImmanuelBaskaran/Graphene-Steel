package com.graphenesteel.renderengine.meshhandler;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OBJMeshLoader implements MeshLoader{
    @Override
    public RawMesh load(String path) {
        FileReader fr = null;
        try{
            fr = new FileReader(new File("res/"+path+".obj"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(fr);
        String line;
        List<Vector3f> verticies = new ArrayList<>();
        List<Vector2f> textures = new ArrayList<>();
        List<Vector3f> normals = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();
        float[] verticesArray = null;
        float[] normalsArray = null;
        float[] textureArray = null;
        int[] indicesArray = null;

        try{
            while (true){
                line = reader.readLine();
                String[] current = line.split(" ");
                if(line.startsWith("v ")){
                    Vector3f vertex = new Vector3f( Float.parseFloat(current[1]),
                                                    Float.parseFloat(current[2]),
                                                    Float.parseFloat(current[3]));
                    verticies.add(vertex);
                }
                if(line.startsWith("vt ")){
                    Vector2f vertex = new Vector2f( Float.parseFloat(current[1]),
                            Float.parseFloat(current[2]));
                    textures.add(vertex);
                }
                if(line.startsWith("vn ")){
                    Vector3f vertex = new Vector3f( Float.parseFloat(current[1]),
                            Float.parseFloat(current[2]),
                            Float.parseFloat(current[3]));
                    normals.add(vertex);
                }
                if(line.startsWith("f ")){
                    textureArray = new float[verticies.size()*2];
                    normalsArray = new float[verticies.size()*3];
                    break;
                }
            }

            while(line!=null){
                if(!line.startsWith("f ")){
                    line = reader.readLine();
                    continue;
                }
                String[] current = line.split(" ");
                String[] vertex1 = current[1].split("/");
                String[] vertex2 = current[2].split("/");
                String[] vertex3 = current[3].split("/");

                processVertex(vertex1,indices,textures,normals,textureArray,normalsArray);
                processVertex(vertex2,indices,textures,normals,textureArray,normalsArray);
                processVertex(vertex3,indices,textures,normals,textureArray,normalsArray);

                line = reader.readLine();
            }



        } catch (IOException e) {
            e.printStackTrace();
        }


        verticesArray = new float[verticies.size()*3];
        indicesArray = new int[indices.size()];

        int vertexPointer = 0;
        for(Vector3f vertex:verticies){
            verticesArray[vertexPointer++] = vertex.x;
            verticesArray[vertexPointer++] = vertex.y;
            verticesArray[vertexPointer++] = vertex.z;
        }

        for(int i=0;i<indices.size();i++){
            indicesArray[i] = indices.get(i);
        }
        return new RawMesh(verticesArray,indicesArray,textureArray,normalsArray);
    }

    private static void processVertex(String[] vertexData, List<Integer> indicies,
                                      List<Vector2f> textures,List<Vector3f> normals,float[] textureArray,
                                      float[] normalsArray){
        int currentVertexPointer = Integer.parseInt(vertexData[0])-1;
        indicies.add(currentVertexPointer);
        Vector2f currentTex = textures.get(Integer.parseInt(vertexData[1])-1);
        textureArray[currentVertexPointer*2]=currentTex.x;
        textureArray[currentVertexPointer*2+1]=currentTex.y;
        Vector3f currentNorm = normals.get(Integer.parseInt(vertexData[ 2])-1);
        normalsArray[currentVertexPointer*3] = currentNorm.x;
        normalsArray[currentVertexPointer*3+1] = currentNorm.y;
        normalsArray[currentVertexPointer*3+2] = currentNorm.z;
    }
}
