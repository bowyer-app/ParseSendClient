package com.bowyer.app.parsesendclient.senddemo;

import com.android.datetimepicker.date.DatePickerDialog;
import com.android.datetimepicker.time.RadialPickerLayout;
import com.android.datetimepicker.time.TimePickerDialog;
import com.bowyer.app.parsesendclient.PushSendLogic;
import com.bowyer.app.parsesendclient.senddemo.model.ParsePushModel;
import com.dd.processbutton.iml.ActionProcessButton;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class PushSendActivity extends ActionBarActivity
        implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private static final String TIME_PATTERN = "HH:mm:ss";

    @InjectView(R.id.push_title)
    EditText mPushTitle;

    @InjectView(R.id.push_message)
    EditText mPushMessage;

    @InjectView(R.id.push_url)
    EditText mPushUrl;

    @InjectView(R.id.date)
    Button mDate;

    @InjectView(R.id.time)
    Button mTime;

    @InjectView(R.id.send_push_now)
    ActionProcessButton mSendPushNow;

    @InjectView(R.id.send_scheduling_push)
    ActionProcessButton mSendSchedulingPush;

    private DateFormat dateFormat;

    private SimpleDateFormat timeFormat;

    private Calendar calendar;

    private final String DEF_PUSH_TITLE = "PushSendClient";

    private final String DEF_PUSH_MESSAGE = "Push send Test Message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_send);
        ButterKnife.inject(this);

        calendar = Calendar.getInstance();
        dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
        timeFormat = new SimpleDateFormat(TIME_PATTERN, Locale.getDefault());
        update();
    }

    @OnClick(R.id.date)
    void onClickDate() {
        DatePickerDialog
                .newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show(
                getFragmentManager(), "datePicker");
    }

    @OnClick(R.id.time)
    void onClickTime() {
        TimePickerDialog.newInstance(this, calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), true).show(getFragmentManager(), "timePicker");
    }

    @OnClick(R.id.send_push_now)
    void sendPushNow() {
        ParsePushModel model = getPushData();
        if (!validatePushData(model)) {
            return;
        }

        mSendPushNow.setProgress(50);
        String[] channel = new String[1];
        channel[0] = "demo";

        PushSendLogic.sendPush(model, channel,
                new PushSendLogic.PushSendCallBack() {
                    @Override
                    public void onSuccess() {
                        mSendPushNow.setProgress(100);
                    }

                    @Override
                    public void onFailure(String message) {
                        mSendPushNow.setProgress(-1);
                    }
                });
    }

    @OnClick(R.id.send_scheduling_push)
    void sendPush() {

        ParsePushModel model = getPushData();
        if (!validatePushData(model)) {
            return;
        }

        mSendSchedulingPush.setProgress(50);

        String[] channel = new String[1];
        channel[0] = "demo";

        PushSendLogic.sendSchedulingPush(model, calendar, channel,
                new PushSendLogic.PushSendCallBack() {
                    @Override
                    public void onSuccess() {
                        mSendSchedulingPush.setProgress(100);
                    }

                    @Override
                    public void onFailure(String message) {
                        mSendSchedulingPush.setProgress(-1);
                    }
                });
    }

    private boolean validatePushData(ParsePushModel model) {
        if (model == null) {
            Toast.makeText(this, "Invalid Url.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private ParsePushModel getPushData() {
        String title = mPushTitle.getText().toString();
        String message = mPushMessage.getText().toString();
        String url = mPushUrl.getText().toString();

        if (!TextUtils.isEmpty(url) && !URLUtil.isValidUrl(url)) {
            return null;
        }

        String pushTitle = TextUtils.isEmpty(title) ? DEF_PUSH_TITLE : title;
        String pushMessage = TextUtils.isEmpty(message) ? DEF_PUSH_MESSAGE : message;
        String pushUrl = TextUtils.isEmpty(url) ? null : url;

        return ParsePushModel.to().setTitle(pushTitle).setMessage(
                pushMessage).setUrl(pushUrl);
    }

    private void update() {
        mDate.setText(dateFormat.format(calendar.getTime()));
        mTime.setText(timeFormat.format(calendar.getTime()));
    }

    @Override
    public void onDateSet(DatePickerDialog dialog, int year, int monthOfYear, int dayOfMonth) {
        calendar.set(year, monthOfYear, dayOfMonth);
        update();
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        update();
    }
}
