package com.example.multitouch;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity
{
	TextView textView = null;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		textView = (TextView) findViewById(R.id.textView);
		textView.setText("颱風「鸚鵡」朝日本前進 外島強風"
				+ "中央氣象局昨天下午兩點也宣布，第廿號颱風「鸚鵡」在關島附近形成，"
				+ "但預測將往北再轉東北朝日本前進，週三前對台灣無直接影響，但沿海及外島週日起可能出現強風。"
				+ "中央氣象局預報員顏增璽說，新一波東北季風來襲，週日下半天北台灣天氣將轉涼，"
				+ "當天北部高溫降到廿八到廿九度，中南部不受影響，高溫還有卅一到卅二度，"
				+ "但下週一起北部高溫一口氣將降到廿三到廿四度，低溫可能降至十八到十九度，"
				+ "中部低溫約廿到廿一度，南部低溫廿二到廿三度，週三才會逐漸回暖。");
		textView.setOnTouchListener(new ZoomListenter());
	}
}
