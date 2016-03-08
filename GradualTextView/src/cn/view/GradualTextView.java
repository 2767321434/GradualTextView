package cn.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

public class GradualTextView extends TextView
{

    private int mViewWidth;
    private Paint mPaint;
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;
    private int mTranslate;
    private int firstColor,nextColor;
    private int delay;
    public GradualTextView(Context context, AttributeSet attrs) {
	super(context, attrs);
	 //获取xml中的属性值
	 TypedArray arry=context.obtainStyledAttributes(attrs, R.styleable.GradualTextView);
	 firstColor=arry.getInt(R.styleable.GradualTextView_firstColor, Color.BLUE);//渐变的第一个颜色，默认蓝色
	 nextColor=arry.getInt(R.styleable.GradualTextView_nextColor,Color.RED);//渐变的第二个颜色，默认红色
	 delay=arry.getInt(R.styleable.GradualTextView_delay, 100);//渐变的延迟，默认100ms
	// TODO 自动生成的构造函数存根
   }
   
    /*
    首先在onSizeChanged()中进行初始化.
    mViewWidth:Textveiw的宽度
    mLinearGradient：不断变化的LinearGradient;
    mGradientMatrix:用于产生变化的矩阵;
    mPaint:TextView的paint对象*/
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // TODO 自动生成的方法存根
     
	super.onSizeChanged(w, h, oldw, oldh);
	if(mViewWidth==0)
	{
	    mViewWidth = getMeasuredWidth();
	    if(mViewWidth>0)
	    {
		mPaint=getPaint();
		mLinearGradient=new LinearGradient(0,0,mViewWidth,0,new int[]{firstColor,nextColor,firstColor},null,Shader.TileMode.CLAMP);
		mPaint.setShader(mLinearGradient);
		mGradientMatrix=new Matrix();
		
	    }
	}
    }

    
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO 自动生成的方法存根
	
        super.onDraw(canvas);
        if(mGradientMatrix!=null){
            mTranslate+=mViewWidth/5;
            if(mTranslate>2*mViewWidth)
            {
        	mTranslate=-mViewWidth;
            }
            mGradientMatrix.setTranslate(mTranslate, 0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(delay);
        }
    }
 

}
