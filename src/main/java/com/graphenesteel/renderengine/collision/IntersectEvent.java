package com.graphenesteel.renderengine.collision;

public class IntersectEvent {
    private boolean doesIntersect;
    private float distance;

    public IntersectEvent(boolean doesIntersect, float distance) {
        this.doesIntersect = doesIntersect;
        this.distance = distance;
    }

    public float getDistance() {
        return distance;
    }

    public boolean isDoesIntersect() {
        return doesIntersect;
    }
}
