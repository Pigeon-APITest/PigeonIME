package se.soprasteria.pigeonime;

public interface PigeonIMEHandleMessage {
    void writeTextWithControlledSpeed(String message, int millisBetweenKeystrokes);
    void writeTextB64WithControlledSpeed(String data, int millisBetweenKeystrokes);
}
