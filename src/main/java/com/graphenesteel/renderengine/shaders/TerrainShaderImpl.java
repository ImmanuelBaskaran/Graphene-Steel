package com.graphenesteel.renderengine.shaders;


import com.graphenesteel.renderengine.entities.Camera;
import com.graphenesteel.renderengine.maths.MatrixUtils;
import org.lwjgl.util.vector.Matrix4f;

public class TerrainShaderImpl extends Shader{

    private static final String VERTEX_FILE = "src\\main\\java\\com\\graphenesteel\\renderengine\\shaders\\vertexShader.txt";
    private static final String FRAGMENT_FILE = "src\\main\\java\\com\\graphenesteel\\renderengine\\shaders\\fragmentShaderTerrain.txt";

    private  int loc_transformation;
    private  int loc_projection;
    private  int loc_view;
    private  int loc_background;
    private  int loc_r;
    private  int loc_g;
    private  int loc_b;
    private  int loc_blend;

    public TerrainShaderImpl() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override

    public void getAllUniforms() {

        loc_transformation=super.getUniformLocation("transformation");
        loc_projection=super.getUniformLocation("projection");
        loc_view=super.getUniformLocation("view");


        loc_background=super.getUniformLocation("background");
        loc_r=super.getUniformLocation("r");
        loc_g=super.getUniformLocation("g");
        loc_b=super.getUniformLocation("b");
        loc_blend=super.getUniformLocation("blendMap");
    }


    public void ConnectTextureUnits(){

        super.loadInt(loc_background,0);
        super.loadInt(loc_r,1);
        super.loadInt(loc_g,2);
        super.loadInt(loc_b,3);
        super.loadInt(loc_blend,4);
        System.out.println("Boogie Bus");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1,"textureCoords");
    }

    public void loadTransformationMatrix(Matrix4f matrix)
    {
        super.loadMatrix(loc_transformation,matrix);
    }
    public void loadProjectionMatrix(Matrix4f matrix)
    {

        super.loadMatrix(loc_projection,matrix);
    }
    public void loadView(Camera camera)
    {
        Matrix4f view = MatrixUtils.createViewMatrix(camera);
        super.loadMatrix(loc_view,view);
    }


}