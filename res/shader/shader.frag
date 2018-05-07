#version 400

in vec2 out_textureCoords;

out vec4 fragColor;

uniform sampler2D textureSampler;

void main(void){
    fragColor = texture(textureSampler, out_textureCoords);
}