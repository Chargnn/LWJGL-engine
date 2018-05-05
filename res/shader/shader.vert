#version 400

layout (location = 0) in vec3 vertices;

out vec4 color;

uniform mat4 transformationMatrix;

void main(void){
    gl_Position = transformationMatrix * vec4(vertices, 1.0);

    color = vec4(clamp(vertices, 0.0, 1.0), 1.0);
}