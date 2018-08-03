package com.horizoncorp.rednebula.entities;

import com.graphenesteel.renderengine.collision.BoundingSphere;
import com.graphenesteel.renderengine.TexturedModelResource;
import com.graphenesteel.renderengine.collision.IntersectEvent;
import com.graphenesteel.renderengine.entities.Entity;
import org.lwjgl.util.vector.Vector;
import org.lwjgl.util.vector.Vector3f;

public class Lunaro extends Entity implements BoundingSphere {



    public Lunaro(TexturedModelResource model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        super(model, position, rotX, rotY, rotZ, scale);
    }

    @Override
    public IntersectEvent intersect(BoundingSphere sphere) {

        float distance = this.getRadius()+sphere.getRadius();
        Vector3f diff = new Vector3f();
        Vector3f.sub(sphere.getPosition(),getPosition(),diff);
        float centerDistance = diff.length();
        if(centerDistance<distance){
            return new IntersectEvent(true,centerDistance-distance);
        }else{
            return new IntersectEvent(false,centerDistance-distance);
        }
    }

    @Override
    public float getRadius() {
        return 1;
    }
    @Override
    public Vector3f getPosition() {
        return super.getPosition();
    }
}
