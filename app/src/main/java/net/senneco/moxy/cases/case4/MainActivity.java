package net.senneco.moxy.cases.case4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import net.senneco.moxy.cases.R;

public class MainActivity extends MvpAppCompatActivity implements HelloWorldView {
	@InjectPresenter
	HelloWorldPresenter mHelloWorldPresenter;
	private TextView mTimerTextView;
	private View mMessageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTimerTextView = (TextView) findViewById(R.id.timer_text_view);
	}

	@Override
	public void showTimer() {
		mTimerTextView.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideTimer() {
		mTimerTextView.setVisibility(View.GONE);
	}

	@Override
	public void setTimer(int seconds) {
		mTimerTextView.setText(getString(R.string.timer, seconds));
	}

	@Override
	public void showMessage(int message) {
		ViewGroup rootView = (ViewGroup) findViewById(R.id.activity_main);
		mMessageView = LayoutInflater.from(this).inflate(R.layout.item_message, rootView, false);
		rootView.addView(mMessageView);

		((TextView) mMessageView.findViewById(R.id.message_text_view)).setText(message);
		mMessageView.findViewById(R.id.close_button).setOnClickListener(view -> mHelloWorldPresenter.onDismissMessage());
	}

	@Override
	public void hideMessage() {
		ViewGroup rootView = (ViewGroup) findViewById(R.id.activity_main);
		rootView.removeView(mMessageView);
	}
}
