package se.soprasteria.pigeonime;

import android.content.IntentFilter;
import android.inputmethodservice.InputMethodService;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputConnection;

import java.io.UnsupportedEncodingException;

public class PigeonIME extends InputMethodService implements PigeonIMEHandleMessage {

    private AdbBroadcastReceiver mAdbBroadcastReceiver;


    @Override
    public View onCreateInputView() {
        View mInputView = getLayoutInflater().inflate(R.layout.pigeonime, null);
        setupAdbTextReceiver();
        return mInputView;
    }

    private void setupAdbTextReceiver() {
        mAdbBroadcastReceiver = new AdbBroadcastReceiver();
        mAdbBroadcastReceiver.setPigeonIMEHandleText(this);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(AdbBroadcastReceiver.IME_TEXT);
        intentFilter.addAction(AdbBroadcastReceiver.IME_TEXT_B64);
        registerReceiver(mAdbBroadcastReceiver, intentFilter);
    }

    public void onDestroy() {
        unregisterReceiver(mAdbBroadcastReceiver);
        super.onDestroy();
    }

    @Override
    public void writeTextWithControlledSpeed(String message, int millisBetweenKeystrokes) {
        simulateKeystrokesWithControlledSpeed(message, millisBetweenKeystrokes);
    }

    @Override
    public void writeTextB64WithControlledSpeed(String data, int millisBetweenKeystrokes) {
        byte[] b64 = Base64.decode(data, Base64.DEFAULT);
        try {
            String message = new String(b64, "UTF-8");
            simulateKeystrokesWithControlledSpeed(message, millisBetweenKeystrokes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    private void simulateKeystrokesWithControlledSpeed(String message, int millisBetweenKeystrokes) {
        InputConnection inputConnection = getCurrentInputConnection();
        if (message != null && millisBetweenKeystrokes >= 0 && inputConnection != null) {
            for (char c : message.toCharArray()) {
                inputConnection.commitText(String.valueOf(c), 1);
                sleep(millisBetweenKeystrokes);
            }
        }
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
