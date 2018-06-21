package com.graphenesteel.renderengine;

import com.graphenesteel.renderengine.meshhandler.RawMesh;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class BasicRenderer implements Renderer{

    public void prepare() {
        GL11.glClearColor(1, 0, 0, 1);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void render(RawMesh model) {
        GL30.glBindVertexArray(model.getVAOID());
        GL20.glEnableVertexAttribArray(0);
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getVertexCount());
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }


}
