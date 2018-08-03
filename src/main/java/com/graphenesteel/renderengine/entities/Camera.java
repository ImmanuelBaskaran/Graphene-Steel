package com.graphenesteel.renderengine.entities;


import com.graphenesteel.renderengine.input.Keyboard;
import com.graphenesteel.renderengine.input.Mouse;
import org.lwjgl.glfw.GLFW;

import org.lwjgl.util.vector.Vector3f;

public class Camera {

    private Vector3f position = new Vector3f(0,4,0);
    private float pitch;
    private float yaw;
    private float roll;
    private float speedZ,speedX;
    public static float dYaw,dPitch;
    public Camera(){}

    public void checkInputs(){
        if(Keyboard.isKeyDown(GLFW.GLFW_KEY_W)){
            speedZ=-0.5f;
        }
        else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_S)){
            speedZ=0.5f;
        }else{
            speedZ=0;
        }
        if(Keyboard.isKeyDown(GLFW.GLFW_KEY_D)){
            speedX=0.5f;
        }
        else if(Keyboard.isKeyDown(GLFW.GLFW_KEY_A)){
            speedX=-0.5f;
        }else{
            speedX=0;
        }

        if(Keyboard.isKeyDown(GLFW.GLFW_KEY_ESCAPE)){
            Mouse.locked = false;
        }

        if(Mouse.locked) {

            dYaw = (float) (Mouse.getDx() * 0.5);
            dPitch = (float) (Mouse.getDy() * 0.5);


        }
    }

    public void move(){
        checkInputs();

        yaw-=dYaw;
        pitch-=dPitch;
        if(pitch>90){
            pitch=(float) 90;
        }if(pitch<-90){
            pitch=(float)-90;
        }
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }


    public void setPosition(Vector3f position) {
        position.y=position.y+4;
        this.position=position;
    }
}