#version 400

in vec2 out_textureCoords;
in vec3 surfaceNormal;
in vec3 toLightVector;

out vec4 fragColor;

// texture
uniform sampler2D textureSampler;

// light
uniform vec3 lightColor;

void main(void){

    vec3 norm_normal = normalize(surfaceNormal);
    vec3 norm_ligthVector = normalize(toLightVector);
    float brightness = max(dot(norm_normal, norm_ligthVector), 0.2);
    vec3 diffuse = brightness * lightColor;

    fragColor = vec4(diffuse, 1.0) * texture(textureSampler, out_textureCoords);
}