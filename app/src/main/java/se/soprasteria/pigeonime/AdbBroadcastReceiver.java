package se.soprasteria.pigeonime;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AdbBroadcastReceiver extends BroadcastReceiver {

    public static final String IME_TEXT = "PIGEON_INPUT_TEXT";
    public static final String IME_TEXT_B64 = "PIGEON_INPUT_B64";

    private static final String MESSAGE = "msg";
    private static final String MILLIS_BETWEEN_KEYSTROKES = "dt";

    private PigeonIMEHandleMessage mPigeonIMEHandleMessage;

    public void setPigeonIMEHandleText(PigeonIMEHandleMessage mPigeonIMEHandleMessage) {
        this.mPigeonIMEHandleMessage = mPigeonIMEHandleMessage;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        if (IME_TEXT.equals(intent.getAction())) {
            String message = intent.getStringExtra(MESSAGE);
            int millisBetweenKeystrokes = intent.getIntExtra(MILLIS_BETWEEN_KEYSTROKES, -1);
            if (mPigeonIMEHandleMessage != null) {
                mPigeonIMEHandleMessage.writeTextWithControlledSpeed(message, millisBetweenKeystrokes);
            }
        }
        if (IME_TEXT_B64.equals(intent.getAction())) {
            String data = intent.getStringExtra(MESSAGE);
            int millisBetweenKeystrokes = intent.getIntExtra(MILLIS_BETWEEN_KEYSTROKES, -1);
            if (mPigeonIMEHandleMessage != null) {
                mPigeonIMEHandleMessage.writeTextB64WithControlledSpeed(data, millisBetweenKeystrokes);
            }
        }
    }

}
