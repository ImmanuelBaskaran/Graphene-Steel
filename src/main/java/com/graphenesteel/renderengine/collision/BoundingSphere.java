package com.graphenesteel.renderengine.collision;

import org.lwjgl.util.vector.Vector3f;

public interface BoundingSphere {


    IntersectEvent intersect(BoundingSphere sphere);
    float getRadius();
    Vector3f getPosition();
}
