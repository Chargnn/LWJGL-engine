package com.chargnn.modelObject;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

public class Model {

    private String id;
    private int vaoID;
    private int vertexCount;

    private List<String> bindings = new ArrayList<>();

    public Model(String id){
        this.id = id;
        vaoID = createVaoID();
    }

    public void pushVertices(float[] vertices){
        bindings.add("vertices");

        GL30.glBindVertexArray(vaoID);

        storeInVbo(0, vertices, 3);
        vertexCount = vertices.length / 3;

        GL30.glBindVertexArray(0);
    }

    public void pushIndices(int[] indices){
        bindings.add("indices");

        GL30.glBindVertexArray(vaoID);

        storeInVbo(indices);
        vertexCount = indices.length;

        GL30.glBindVertexArray(0);
    }

    public void render(){
        if(!bindings.contains("vertices"))
            System.err.println("No vertices for id: [" + id + "]");

        if(bindings.contains("indices")) { // draw with indices
            GL30.glBindVertexArray(vaoID);
            GL20.glEnableVertexAttribArray(0);

            GL11.glDrawElements(GL11.GL_TRIANGLES, vertexCount, GL11.GL_UNSIGNED_INT, 0);

            GL20.glDisableVertexAttribArray(0);
            GL30.glBindVertexArray(0);
        } else {                         // draw without indices
            GL30.glBindVertexArray(vaoID);
            GL20.glEnableVertexAttribArray(0);

            GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, vertexCount);

            GL20.glDisableVertexAttribArray(0);
            GL30.glBindVertexArray(0);
        }
    }

    private int createVaoID(){
        return GL30.glGenVertexArrays();
    }

    private void storeInVbo(int index, float[] data, int vertexSize){
        int vboID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);

        FloatBuffer buffer = (FloatBuffer) BufferUtils.createFloatBuffer(data.length).put(data).flip();
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(index, vertexSize, GL11.GL_FLOAT, false, 0, 0);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    private void storeInVbo(int[] data){
        int vboID = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);

        IntBuffer buffer = (IntBuffer) BufferUtils.createIntBuffer(data.length).put(data).flip();
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }
}
