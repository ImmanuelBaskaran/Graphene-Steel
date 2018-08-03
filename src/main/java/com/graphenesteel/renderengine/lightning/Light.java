package com.graphenesteel.renderengine.lightning;

import org.lwjgl.util.vector.Vector3f;

public class Light {

    private Vector3f direction;
    private Vector3f color;
    private float intensity;

    public Light(Vector3f direction, Vector3f color, float intensity) {
        this.direction = direction;
        this.color = color;
        this.intensity = intensity;
    }

    public Vector3f getDirection() {
        return direction;
    }

    public Vector3f getColor() {
        return color;
    }

    public float getIntensity() {
        return intensity;
    }
}
