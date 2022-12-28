#version 400 core

layout(location = 0) in vec3 a_position;
layout(location = 1) in vec2 a_uv;
layout(location = 2) in vec3 a_normal;
layout(location = 3) in vec3 a_color;

out vec3 normal;
out vec4 position;
out vec2 uv;
out vec3 color;
out vec3 toCamera;

uniform vec3 u_color[100];
uniform mat4 u_transforms[100];
uniform mat4 u_view;
uniform mat4 u_projection;

void main() {
    vec4 world = u_transforms[gl_InstanceID] * vec4(a_position, 1);
    gl_Position = u_projection * u_view * world;
    position = world;
    normal = (u_transforms[gl_InstanceID] * vec4(a_normal, 0)).xyz;
    color = u_color[gl_InstanceID] * a_color;
    uv = a_uv;
    toCamera = (inverse(u_view) * vec4(0,0,0,1)).xyz - world.xyz;
}

#split

#version 400 core

in vec3 normal;
in vec4 position;
in vec2 uv;
in vec3 color;
in vec3 toCamera;

uniform sampler2D tex;
uniform float u_shine;

layout(location = 0) out vec4 o_color;
layout(location = 1) out vec4 o_normal;
layout(location = 2) out vec4 o_position;
layout(location = 3) out vec4 o_shine;

void main() {
    o_color = vec4(color,1);
    o_normal = vec4(normal, 1);
    o_position = position;
    o_shine = vec4(toCamera, 1) * u_shine;
}