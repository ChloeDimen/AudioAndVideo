package com.dimen.audioandvideo.glsurfaceview;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * 文件名：com.dimen.audioandvideo.glsurfaceview
 * 描    述：
 * 作    者：Dimen
 * 时    间：2019/12/6
 */
public class BackgroundRender extends BaseRenderer implements GLSurfaceView.Renderer {
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.0f,0.0f,0.0f,1.0f);



    }
    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        // 渲染窗口大小发生改变或者屏幕方法发生变化时候回调
        GLES20.glViewport(0,0,width,height);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        //执行渲染工作
        //Redraw background color
        GLES20.glClearColor(GLES20.GL_COLOR_BUFFER_BIT,0f,0f,0f);
    }
    public int loadShader(int type, String shaderCode) {
        //根据type创建顶点着色器或者片元着色器
        //创建一个vertex shader类型(GLES20.GL_VERTEX_SHADER)
        //或一个fragment shader类型(GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);
        //将资源加入到着色器中，并编译
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }


}
