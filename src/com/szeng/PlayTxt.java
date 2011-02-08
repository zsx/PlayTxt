package com.szeng;

import java.awt.Container;
import java.io.*;
import com.amazon.kindle.kindlet.AbstractKindlet;
import com.amazon.kindle.kindlet.KindletContext;
import com.amazon.kindle.kindlet.media.AudioManager;
import com.amazon.kindle.kindlet.media.MediaSource;
import com.amazon.kindle.kindlet.media.Audio;
import com.amazon.kindle.kindlet.ui.KTextArea;

public class PlayTxt  extends AbstractKindlet {

    private Container root;
	private KindletContext context;
	private AudioManager am;

    public void create(KindletContext context) {
		this.context = context;
        root = context.getRootContainer();
		am = context.getAudioManager();
    }

    public void start() {
        KTextArea label = new KTextArea("Null");
        try {
			File home = context.getHomeDirectory();
			FileInputStream fis = new FileInputStream(home.getAbsolutePath() + "/test.mp3");
			MediaSource mp3 = new MediaSource(fis);
			Audio player = am.createAudioPlayer(mp3);
			player.play();
        } catch (Throwable e) {
            String err = e.toString() + "\n";
            StackTraceElement [] es = e.getStackTrace();
            for (int i = 0; i < es.length; i ++)
            {
                err += es[i].toString() + "\n";
            }
            label.setText(label.getText() + err);
        }
        root.add(label);
        }
}
