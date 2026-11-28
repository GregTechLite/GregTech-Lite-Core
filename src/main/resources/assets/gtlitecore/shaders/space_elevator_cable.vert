#version 120

// Ported from GTNH-Intergalactic's space elevator cable shader.

uniform float u_BaseY;
uniform float u_CableHeight;

varying vec2 v_TexCoord;
varying float v_yPos;

void main()
{
    gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;
    v_TexCoord = gl_MultiTexCoord0.xy;
    v_yPos = max(u_BaseY + gl_Vertex.y, 0.0);
}
