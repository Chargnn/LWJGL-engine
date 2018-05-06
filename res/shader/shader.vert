#version 400

layout (location = 0) in vec3 vertices;

out vec4 color;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;

void main(void){
    gl_Position = projectionMatrix * viewMatrix * transformationMatrix * vec4(vertices, 1.0);

    color = vec4(clamp(vertices, 0.0, 1.0), 1.0);
}