package com.graphenesteel.renderengine;

public class WindowManagerIntegrationTest {


    public static void main(String[] args) {
        WindowManager window = new WindowManager(400,400);
        window.update();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        window.destroy();
    }

}
