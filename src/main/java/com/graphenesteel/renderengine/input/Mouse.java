package com.graphenesteel.renderengine.input;

import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWCursorPosCallback;

// Our MouseHandler class extends the abstract class
// abstract classes should never be instantiated so here
// we create a concrete that we can instantiate
public class Mouse extends GLFWCursorPosCallback {

    private static double xpos,ypos;
    private static double dx,dy;
    public static boolean locked = false;


    @Override
    public void invoke(long window, double xpos, double ypos) {
        // TODO Auto-generated method stub
        // this basically just prints out the X and Y coordinates
        // of our mouse whenever it is in our window


        dx = Mouse.xpos-xpos;
        dy = Mouse.ypos-ypos;
        Mouse.xpos = xpos;
        Mouse.ypos = ypos;
        if(locked){
            glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
        }else{
            glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
        }

    }

    public static double getDx() {
        double temp = dx;
        dx=0;
        return temp;
    }

    public static double getDy() {
        double temp = dy;
        dy=0;
        return temp;
    }

    public static double getXpos() {
        return xpos;
    }

    public static double getYpos() {
        return ypos;
    }
}