package com.graphenesteel.renderengine.input;

import org.lwjgl.glfw.GLFWMouseButtonCallback;
import static org.lwjgl.glfw.GLFW.*;
public class MouseButton extends GLFWMouseButtonCallback {
    @Override
    public void invoke(long window, int button, int action, int mods) {
        if(button==GLFW_MOUSE_BUTTON_1){
            Mouse.locked=true;
        }
    }
}
