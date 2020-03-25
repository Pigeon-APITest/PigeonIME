# PigeonIME
PigeonIME is a virtual keyboard for Android automation tests using the Pigeon framework powered by Sopra Steria Sweden. [link]

The keyboard receives the text to type by system broadcast intents using ADB commands. The keyboard receives the text to type by system broadcast intents using ADB commands. The keystrokes are simulated one-by-one with a controlled speed set by the time variable (millisecond between each keystroke).



## How to use
- Install the PigeonIME.apk
- Enable "Pigeon IME" in the "Languages & input" in system settings 
- Set "Pigeon IME" as the default input device
- Alt. enable it and set with ADB command (command seen below)
- Write text to element by sending commands as seen in the examples below through ADB


###### Example commands:
```
Send text to write with time (in ms) between keystrokes.
adb shell am broadcast -a PIGEON_INPUT_TEXT --es msg '[text]' --ei dt [time]

Send text to write using Base64 (if first command doesn't work)
adb shell am broadcast -a PIGEON_INPUT_B64 --es msg `echo '[text]' | base64`

```

Switch to PigeonIME from ADB command:
```
adb shell ime set se.soprasteria.pigeonime/.PigeonIME
```

To send the command to a specific device add following directly after adb:
```
-s [device name]
```
