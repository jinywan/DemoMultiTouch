package com.example.multitouch;

import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class ZoomListenter implements OnTouchListener
{
	float oldDist;
	float textSize;
	TextView textView;

	@Override
	public boolean onTouch(View v, MotionEvent event)
	{
		textView = (TextView) v;
		// 當 textSize = 0 ，就初始化 textSize
		if (textSize == 0)
			textSize = textView.getTextSize();
		// 當textSize < 1 ，就將之設定為1，避免過度的縮小
		else if (textSize < 1)
			textSize = 1;

		switch (event.getAction() & MotionEvent.ACTION_MASK)
		{
		case MotionEvent.ACTION_DOWN://多點觸控，第一手指觸發
			System.out.println("ACTION_DOWN");
			break;
		case MotionEvent.ACTION_POINTER_DOWN://多點觸控，第二手指以上觸發，取第二碰觸點為第二點
			System.out.println("ACTION_POINTER_DOWN");
			oldDist = getHypotenuse(event);
			break;
		case MotionEvent.ACTION_UP:
				System.out.println("ACTION_UP");
				break;
		case MotionEvent.ACTION_POINTER_UP:
			System.out.println("ACTION_POINTER_UP");
			break;
		case MotionEvent.ACTION_MOVE:
			System.out.println("ACTION_MOVE");
			try
			{ 
				float newDist = getHypotenuse(event);
				if (newDist > oldDist + 1)
				{	
					zoom(newDist / oldDist);
					oldDist = newDist;
				}8
				if (newDist < oldDist - 1)
				{
					zoom(newDist / oldDist);
					oldDist = newDist;
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			break;
		}
		return true;
	}

	private void zoom(float f)
	{
		textView.setTextSize(textSize *= f);
	}

	// 利用直角三角形求斜邊的觀念來算出兩指之間的距離
	private float getHypotenuse(MotionEvent event)
	{
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return FloatMath.sqrt(x * x + y * y);
	}

}
