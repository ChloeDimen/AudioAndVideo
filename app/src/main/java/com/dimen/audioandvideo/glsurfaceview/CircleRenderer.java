package com.dimen.audioandvideo.glsurfaceview;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * 文件名：com.dimen.audioandvideo.glsurfaceview
 * 描    述：
 * 作    者：Dimen
 * 时    间：2019/12/6
 */
public class CircleRenderer extends BackgroundRender implements GLSurfaceView.Renderer {
    String vertexShaderCode = "attribute vec4 vPosition;\n" +
            "uniform mat4 vMatrix;\n" +
            "void main() {\n" +
            "  gl_Position = vMatrix*vPosition;\n" +
            "}" ;

    String fragmentShaderCode = "precision mediump float;\n" +
            " uniform vec4 vColor;\n" +
            " void main() {\n" +
            "     gl_FragColor = vColor;\n" +
            " }";
    int mProgram;

    private FloatBuffer vertexBuffer;
    private float[] circlrCoods;
    float color[] = { 1.0f, 1.0f, 1.0f, 1.0f }; //顶点统一白色

    public CircleRenderer() {
        //设置所有坐标
        circlrCoods = createPositions();
        //float[] → FloatBuffer
        ByteBuffer bb = ByteBuffer.allocateDirect(circlrCoods.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(circlrCoods);
        vertexBuffer.position(0);
    }

    private float[]  createPositions(){
        ArrayList<Float> data=new ArrayList<>();
        data.add(0.0f);             //设置圆心坐标
        data.add(0.0f);
        data.add(0.0f);
        //分成100条边，绘制出来应该很像圆了
        float radius = 0.5f; //半径1
        float angDegSpan=360f/100; //依次递加的角度
        for(float i=0;i<360+angDegSpan;i+=angDegSpan){
            data.add((float)(radius*Math.cos(i*Math.PI/180f))); //x
            data.add((float) (radius*Math.sin(i*Math.PI/180f))); //y
            data.add(0.0f); //z
        }
        float[] f=new float[data.size()];
        for (int i=0;i<f.length;i++){
            f[i]=data.get(i);
        }
        return f;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        int vetexShader = loadShader(GLES20.GL_VERTEX_SHADER , vertexShaderCode);
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER , fragmentShaderCode);
        //添加着色器
        mProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(mProgram,vetexShader);
        GLES20.glAttachShader(mProgram,fragmentShader);
        GLES20.glLinkProgram(mProgram);
    }

    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0,0,width,height);
        float ratio = (float) width/height;
        //设置矩阵
        Matrix.frustumM(mProjectMatrix,0,-ratio,ratio,-1,1,3,20);
        Matrix.setLookAtM(mViewMatrix,0,0f,0f,7.0f,0f,0f,0f,0f,1.0f,0f);
        Matrix.multiplyMM(mMVPMatrix,0,mProjectMatrix,0,mViewMatrix,0);
    }

    int mMatrixHandler;
    int mPositionHandle;
    int vColorHandle;
    @Override
    public void onDrawFrame(GL10 gl) {
        Log.e("TAG","error");
        GLES20.glUseProgram(mProgram);
        mMatrixHandler = GLES20.glGetUniformLocation(mProgram,"vMatrix");
        GLES20.glUniformMatrix4fv(mMatrixHandler,1,false,mMVPMatrix,0);

        mPositionHandle = GLES20.glGetAttribLocation(mProgram,"vPosition");
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        GLES20.glVertexAttribPointer(
                mPositionHandle,
                3,
                GLES20.GL_FLOAT,
                false,
                12,
                vertexBuffer
        );

        vColorHandle = GLES20.glGetUniformLocation(mProgram,"vColor");
        GLES20.glUniform4fv(vColorHandle,1,color,0);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN , 0 , circlrCoods.length/3);
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }


}
