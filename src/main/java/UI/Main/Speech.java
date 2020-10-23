package UI.Main;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Speech {
    private final Voice speech;

    public Speech(String name) {
        this.speech = VoiceManager.getInstance().getVoice(name);
        this.speech.allocate();
    }

    public void say(String someThing) {
        this.speech.speak(someThing);
    }
}
