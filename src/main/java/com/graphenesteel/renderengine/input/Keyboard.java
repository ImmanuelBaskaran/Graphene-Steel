package com.graphenesteel.renderengine.input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class Keyboard extends GLFWKeyCallback {

        public static boolean[] keys = new boolean[65536];

        // The GLFWKeyCallback class is an abstract method that
        // can't be instantiated by itself and must instead be extended
        //
        @Override
        public void invoke(long window, int key, int scancode, int action, int mods) {
            // TODO Auto-generated method stub
            keys[key] = action != GLFW.GLFW_RELEASE;
        }

        // boolean method that returns true if a given key
        // is pressed.
        public static boolean isKeyDown(int keycode) {
            return keys[keycode];
        }
}
