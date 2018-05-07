#version 400

in vec2 color;

out vec4 fragColor;

uniform sampler2D textureSampler;

void main(void){
    fragColor = texture(textureSampler, color);
}