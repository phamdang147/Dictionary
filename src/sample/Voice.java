package sample;

import com.sun.speech.freetts.VoiceManager;

public class Voice {
    private final com.sun.speech.freetts.Voice voice;

    public Voice(String name) {
        this.voice = VoiceManager.getInstance().getVoice(name);
        this.voice.allocate();
    }

    public void say(String someThing) {
        this.voice.speak(someThing);
    }
}
