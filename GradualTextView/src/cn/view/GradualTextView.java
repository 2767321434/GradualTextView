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
	 TypedArray arry=context.obtainStyledAttributes(attrs, R.styleable.GradualTextView);
	 firstColor=arry.getInt(R.styleable.GradualTextView_firstColor, Color.BLUE);
	 nextColor=arry.getInt(R.styleable.GradualTextView_nextColor,Color.RED);
	 delay=arry.getInt(R.styleable.GradualTextView_delay, 100);
	// TODO 自动生成的构造函数存根
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

}
