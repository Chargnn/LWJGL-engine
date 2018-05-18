#version 400

layout (location = 0) in vec3 vertices;
layout (location = 1) in vec2 textureCoords;
layout (location = 2) in vec3 normals;

out vec2 out_textureCoords;
out vec3 surfaceNormal;
out vec3 toLightVector;

// camera
uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

// Light
uniform vec3 lightPosition;

void main(void){
    vec4 worldVertices = transformationMatrix * vec4(vertices, 1.0);

    gl_Position = projectionMatrix * viewMatrix * worldVertices;
    out_textureCoords = textureCoords;

    surfaceNormal = (transformationMatrix * vec4(normals, 0.0)).xyz;
    toLightVector = lightPosition - worldVertices.xyz;
}