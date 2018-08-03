package com.graphenesteel.renderengine.entities;

import com.graphenesteel.renderengine.TexturedModelResource;
import com.graphenesteel.renderengine.WindowManager;
import com.graphenesteel.renderengine.input.Keyboard;
import com.graphenesteel.renderengine.input.Mouse;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.util.vector.Vector3f;

public class Player extends Entity{

    private float dz,dx,dYaw,dPitch;
    private float pitch;
    private float yaw;
    private float upwardsSpeed = 20,GRAVITY=-100,JUMP_POWER=60;

    Camera camera = null;
    private boolean inAir = false;

    public Player(TexturedModelResource model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        super(model, position, rotX, rotY, rotZ, scale);
    }
    public Player(TexturedModelResource model, Vector3f position, float rotX, float rotY, float rotZ, float scale,Camera camera) {
        super(model, position, rotX, rotY, rotZ, scale);
        this.camera = camera;
    }

    public void move(){
        checkInputs();
        yaw+=Camera.dYaw;
        pitch+=Camera.dPitch;
        this.increaseRotation(0,Camera.dYaw,0);
        float distance = this.dz;
        float dx = (float)(distance*Math.sin(Math.toRadians(yaw)));
        float dz = (float)(distance*Math.cos(Math.toRadians(yaw)));


        float distance2 = this.dx;
        float dz2 = (float)(distance2*Math.sin(Math.toRadians(-yaw)));
        float dx2 = (float)(distance2*Math.cos(Math.toRadians(-yaw)));

        if(super.getPosition().y<0f){
            upwardsSpeed = 0f;
            super.getPosition().y=0f;
            inAir=false;
        }else{
            upwardsSpeed += GRAVITY*WindowManager.getDelta();
        }
        super.increasePosition((dx+dx2)*WindowManager.getDelta(),upwardsSpeed*WindowManager.getDelta(),(dz+dz2)*WindowManager.getDelta());
        if(camera!=null) {
           Vector3f cam = new Vector3f(super.getPosition());

            camera.setPosition(cam);
        }
    }

    private void checkInputs(){
        if(Keyboard.isKeyDown(GLFW.GLFW_KEY_W)){
            dz=-80f;
        }
        else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_S)){
            dz=80f;
        }else{
            dz=0;
        }
        if(Keyboard.isKeyDown(GLFW.GLFW_KEY_D)){
            dx=80f;
        }

        else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_A)){
            dx=-80f;
        }else{
            dx=0;
        }

        if(Keyboard.isKeyDown(GLFW.GLFW_KEY_SPACE)){
            jump();
        }
    }

    private void jump() {
        if(!inAir) {
            upwardsSpeed = JUMP_POWER;
            inAir = true;
        }
    }
}
