package com.graphenesteel.renderengine.shaders;


import com.graphenesteel.renderengine.entities.Camera;
import com.graphenesteel.renderengine.lightning.Light;
import com.graphenesteel.renderengine.maths.MatrixUtils;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class ForwardDirectional extends Shader{

    private static final String VERTEX_FILE = "src\\main\\java\\com\\graphenesteel\\renderengine\\shaders\\forward-directional-vertex.txt";
    private static final String FRAGMENT_FILE = "src\\main\\java\\com\\graphenesteel\\renderengine\\shaders\\forward-directional-fragment.txt";

    private  int loc_transformation;
    private  int loc_projection;
    private  int loc_view;
    private  int loc_lightColour;
    private  int loc_lightPosition;

    public ForwardDirectional() {
        super(VERTEX_FILE, FRAGMENT_FILE);



    }

    @Override
    public void getAllUniforms() {
        loc_transformation=super.getUniformLocation("transformation");
        loc_projection=super.getUniformLocation("projection");
        loc_view=super.getUniformLocation("view");
        loc_lightColour=super.getUniformLocation("lightColour");
        loc_lightPosition=super.getUniformLocation("lightPosition");
    }



    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1,"textureCoords");
        super.bindAttribute(2,"normals");
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
    public void loadLight(Light light){
        super.loadVector(loc_lightColour,light.getColor());
        super.loadVector(loc_lightPosition,light.getDirection());
    }

}