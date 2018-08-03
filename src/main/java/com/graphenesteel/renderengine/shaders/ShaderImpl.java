package com.graphenesteel.renderengine.shaders;


import com.graphenesteel.renderengine.entities.Camera;
import com.graphenesteel.renderengine.maths.MatrixUtils;
import org.lwjgl.util.vector.Matrix4f;

public class ShaderImpl extends Shader{

    private static final String VERTEX_FILE = "src\\main\\java\\com\\graphenesteel\\renderengine\\shaders\\vertexShader.txt";
    private static final String FRAGMENT_FILE = "src\\main\\java\\com\\graphenesteel\\renderengine\\shaders\\fragmentShader.txt";

    private  int loc_transformation;
    private  int loc_projection;
    private  int loc_view;

    public ShaderImpl() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    public void getAllUniforms() {
        loc_transformation=super.getUniformLocation("transformation");
        loc_projection=super.getUniformLocation("projection");
        loc_view=super.getUniformLocation("view");
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