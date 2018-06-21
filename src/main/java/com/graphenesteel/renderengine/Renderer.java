package com.graphenesteel.renderengine;

import com.graphenesteel.renderengine.meshhandler.RawMesh;

public interface Renderer {


    void render(RawMesh model);

    void prepare();

}
