#version 120

// Ported from GTNH-Intergalactic's space elevator cable shader.

uniform sampler2D u_CableTex;
uniform float u_Time;
uniform float u_CableHeight;
uniform vec2 u_GlowU;
uniform vec2 u_GlowV;

varying vec2 v_TexCoord;
varying float v_yPos;

const vec3 GLOW_COLOR = vec3(0.0, 0.65, 1.0);
const vec3 MAX_COLOR = vec3(1.0);

// A wave front travelling up the cable; only the texture region inside u_Glow* lights up.
vec3 frontWave(float front)
{
    float y = v_yPos / u_CableHeight;
    float dist = abs(front - y);
    float lightsOn = dist <= 0.03 ? 1.0 : 0.0;
    float sy = sin(1.57 + (dist * 33.0) * 1.57);
    return GLOW_COLOR * sy * sy * lightsOn;
}

void main()
{
    vec4 tex = texture2D(u_CableTex, v_TexCoord);
    float glowMul = (v_TexCoord.x >= u_GlowU.x && v_TexCoord.x <= u_GlowU.y
        && v_TexCoord.y >= u_GlowV.x && v_TexCoord.y <= u_GlowV.y) ? 1.0 : 0.0;
    gl_FragColor = vec4(min(tex.rgb + frontWave(u_Time) * glowMul, MAX_COLOR), tex.a);
}
