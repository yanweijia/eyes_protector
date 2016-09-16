package cn.yanweijia.eyes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_SeMang_ceshi extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_semang_ceshi);
		// 用来记录现在的位置
		class Now {
			public int now;
			//正确的个数
			public int right;
		}
		final Now now = new Now();
		now.now = 0;
		now.right = 0;
		// 各个题目的答案和测试色盲图片的资源ID
		final int[] answer = { 6, 36, 698, 8096, 896, 835, 652, 83, 6, 56 };
		final int[] id = { R.drawable.semangti1, R.drawable.semangti2,
				R.drawable.semangti3, R.drawable.semangti4,
				R.drawable.semangti5, R.drawable.semangti6,
				R.drawable.semangti7, R.drawable.semangti8,
				R.drawable.semangti9, R.drawable.semangti10 };

		final ImageView imageview = (ImageView) findViewById(R.id.image_semang_ceshi_pic);
		final EditText edittext = (EditText) findViewById(R.id.edittext_semang_answer);
		final Button button = (Button) findViewById(R.id.button_semang_ceshi_back);
		final ProgressBar progress_jindu = (ProgressBar) findViewById(R.id.progress_jindu);
		final TextView textview_count = (TextView) findViewById(R.id.text_semang_count);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Activity_SeMang_ceshi.this,
						Activity_SeMang.class);
				startActivity(intent);
				finish();
			}
		});

		final Button btn_next = (Button) findViewById(R.id.button_semang_ceshi_next);
		btn_next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 点击继续下一个按钮后执行的任务
				if (now.now < 9) {
					imageview.setImageResource(id[now.now + 1]);
					if (edittext.getText().toString()
							.equals(Integer.toString(answer[now.now]))) {
						now.right++;
						Toast.makeText(Activity_SeMang_ceshi.this, "恭喜你,答对了",
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(
								Activity_SeMang_ceshi.this,
								"很遗憾,答错了  \n正确答案是:" + answer[now.now]
										+ "\n你的答案是:"
										+ edittext.getText().toString(),
								Toast.LENGTH_SHORT).show();
					}
					edittext.setText("");
					progress_jindu.setProgress(now.now + 2);
					textview_count.setText(" 题目：" + (now.now + 2) + "/10");
					now.now++;
				} else {
					if (edittext.getText().toString()
							.equals(Integer.toString(answer[now.now]))) {
						now.right++;
						Toast.makeText(Activity_SeMang_ceshi.this, "恭喜你,答对了",
								Toast.LENGTH_SHORT).show();
						Toast.makeText(Activity_SeMang_ceshi.this,
								"总共答对了" + now.right + "道题", Toast.LENGTH_SHORT)
								.show();
					} else {
						Toast.makeText(
								Activity_SeMang_ceshi.this,
								"很遗憾,答错了  \n正确答案是:" + answer[now.now]
										+ "\n你的答案是:"
										+ edittext.getText().toString(),
								Toast.LENGTH_SHORT).show();
					}
					
					Intent intent = new Intent();
					intent.setClass(Activity_SeMang_ceshi.this,
							Activity_SeMang_result.class);
					intent.putExtra("score", Integer.toString(now.right));
					startActivity(intent);
					finish();
				}
			}
		});

	}
}
