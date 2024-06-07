//ExGLRenderer.java OpenGLESのレンダリング処理に関するサンプル
package es.exsample;

import android.opengl.GLSurfaceView;
import java.nio.*;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class SampleGLRenderer implements GLSurfaceView.Renderer {

    private FloatBuffer vertexBuffer;

    public void onSurfaceCreated(GL10 gl10,EGLConfig eglConfig) {
        gl10.glEnableClientState(GL10.GL_VERTEX_ARRAY);  //ポリゴンの頂点データを準備
        float[] vertexs={ -0.5f,0.0f,0.0f,
                0.5f,0.0f,0.0f,
                0.0f,0.5f,0.0f,};
        vertexBuffer=makeFloatBuffer(vertexs);  //頂点データの格納
    }

    public void onSurfaceChanged(GL10 gl10,int w,int h) {
        gl10.glViewport(0,0,w,h);  //ビューポートの設定
    }

    public void onDrawFrame(GL10 gl10) {
        gl10.glClearColor(1.0f,1.0f,1.0f,1.0f);  //背景色の設定
        gl10.glClear(GL10.GL_COLOR_BUFFER_BIT);  //カラーバッファの初期化

        gl10.glColor4f(1.0f,0.0f,0.0f,1.0f);  //ポリゴン色の設定

        gl10.glVertexPointer(3,GL10.GL_FLOAT,0,vertexBuffer);  //頂点データの設定
        gl10.glDrawArrays(GL10.GL_TRIANGLES,0,3);  //ポリゴンの描画
    }

    private FloatBuffer makeFloatBuffer(float[] array) {  //頂点データ格納のためのバッファ
        FloatBuffer fb=ByteBuffer.allocateDirect(array.length*4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        fb.put(array).position(0);
        return fb;
    }
}