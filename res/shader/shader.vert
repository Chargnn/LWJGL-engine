#version 400

layout (location = 0) in vec3 vertices;

out vec4 color;

void main(void){
    gl_Position = vec4(vertices, 1.0);

    color = vec4(vertices, 1.0);
}