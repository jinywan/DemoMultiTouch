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
		// �� textSize = 0 �A�N��l�� textSize
		if (textSize == 0)
			textSize = textView.getTextSize();
		// ��textSize < 1 �A�N�N���]�w��1�A�קK�L�ת��Y�p
		else if (textSize < 1)
			textSize = 1;

		switch (event.getAction() & MotionEvent.ACTION_MASK)
		{
		case MotionEvent.ACTION_DOWN://�h�IĲ���A�Ĥ@���Ĳ�o
			System.out.println("ACTION_DOWN");
			break;
		case MotionEvent.ACTION_POINTER_DOWN://�h�IĲ���A�ĤG����H�WĲ�o�A���ĤG�IĲ�I���ĤG�I
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

	// �Q�Ϊ����T���ΨD���䪺�[���Ӻ�X����������Z��
	private float getHypotenuse(MotionEvent event)
	{
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return FloatMath.sqrt(x * x + y * y);
	}

}
