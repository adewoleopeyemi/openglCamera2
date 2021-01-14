package com.example.openglcam2;

public class MatrixHelper {
    public static void perspectiveM(float[] m, float yFovInDegrees, float aspect,
                                    float n, float f){
        final float angleInRadians = (float) (yFovInDegrees * Math.PI / 180.0);
        final float a = (float) (1.0 / Math.tan(angleInRadians / 2.0));
        m[0] = a / aspect;
        m[1] = 0f;
        m[2] = 0f;
        m[3] = 0f;
        m[4] = 0f;
        m[5] = a;
        m[6] = 0f;
        m[7] = 0f;
        m[8] = 0f;
        m[9] = 0f;
        m[10] = -((f + n) / (f - n));
        m[11] = -1f;
        m[12] = 0f;
        m[13] = 0f;
        m[14] = -((2f * f * n) / (f - n));
        m[15] = 0f;
    }

    public static void mat4f_load_ortho(float[] m,float left, float right, float bottom, float top, float near, float far)
    {
        float r_l = right - left;
        float t_b = top - bottom;
        float f_n = far - near;
        float tx = - (right + left) / (right - left);
        float ty = - (top + bottom) / (top - bottom);
        float tz = - (far + near) / (far - near);

        m[0] = 2.0f / r_l;
        m[1] = 0.0f;
        m[2] = 0.0f;
        m[3] = 0.0f;

        m[4] = 0.0f;
        m[5] = 2.0f / t_b;
        m[6] = 0.0f;
        m[7] = 0.0f;

        m[8] = 0.0f;
        m[9] = 0.0f;
        m[10] = -2.0f / f_n;
        m[11] = 0.0f;

        m[12] = tx;
        m[13] = ty;
        m[14] = tz;
        m[15] = 1.0f;
    }

    public static void mat4f_load_rotation_z(int rotation, float[] mat4f)
    {
        float radians = rotation * (float)Math.PI / 180.0f;
        float s = (float) Math.sin(radians);
        float c = (float) Math.cos(radians);

        mat4f[0] = c;
        mat4f[1] = -s;
        mat4f[2] = 0.0f;
        mat4f[3] = 0.0f;

        mat4f[4] = s;
        mat4f[5] = c;
        mat4f[6] = 0.0f;
        mat4f[7] = 0.0f;

        mat4f[8] = 0.0f;
        mat4f[9] = 0.0f;
        mat4f[10] = 1.0f;
        mat4f[11] = 0.0f;

        mat4f[12] = 0.0f;
        mat4f[13] = 0.0f;
        mat4f[14] = 0.0f;
        mat4f[15] = 1.0f;
    }

    public static void mat4f_load_scale(float scaleX, float scaleY, float scaleZ, float[] mat4f)
    {
        mat4f[0] = scaleX;
        mat4f[1] = 0.0f;
        mat4f[2] = 0.0f;
        mat4f[3] = 0.0f;

        mat4f[4] = 0.0f;
        mat4f[5] = scaleY;
        mat4f[6] = 0.0f;
        mat4f[7] = 0.0f;

        mat4f[8] = 0.0f;
        mat4f[9] = 0.0f;
        mat4f[10] = scaleZ;
        mat4f[11] = 0.0f;

        mat4f[12] = 0.0f;
        mat4f[13] = 0.0f;
        mat4f[14] = 0.0f;
        mat4f[15] = 1.0f;
    }

    public static float aspect_ratio_correction(boolean fillScreen, float backingWidth, float backingHeight, float width, float height)
    {
        float backingAspectRatio = (float)backingWidth / (float)backingHeight;
        float targetAspectRatio = (float)width / (float)height;
        float scalingFactor = 1.0f;

        if (fillScreen)
        {
            if (backingAspectRatio > targetAspectRatio)
            {
                scalingFactor = (float)backingWidth / (float)width;
            }
            else
            {
                scalingFactor = (float)backingHeight / (float)height;
            }
        }

        return scalingFactor;
    }
}
