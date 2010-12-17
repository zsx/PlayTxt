package com.szeng;

import java.awt.Container;
import java.io.*;
import com.amazon.kindle.kindlet.AbstractKindlet;
import com.amazon.kindle.kindlet.KindletContext;
import com.amazon.kindle.kindlet.ui.KTextArea;
public class PlayTxt  extends AbstractKindlet {
        
        private KindletContext ctx;
        private Container root;
        
        public void create(KindletContext context) {
                ctx = context;
                root = ctx.getRootContainer();
        }

        public void start() {
                try {
                		KTextArea t = new KTextArea("Hello, kindle 3!\n");
                		t.append("Home = " + ctx.getHomeDirectory().getAbsolutePath() + "\n");
                		try {
                			File doc = new File(ctx.getHomeDirectory(), "documents");
							if (doc.exists() && doc.isDirectory()){
								File [] flist = doc.listFiles();
								for (int i = 0; i < flist.length; i++){
									String fn = flist[i].getAbsolutePath();
									if (fn.endsWith(".txt")){
										FileInputStream fi = new FileInputStream(flist[i]);
										int len = fi.available();
										byte [] content = new byte[len];
										fi.read(content);
										t.append(new String(content, "UTF-8"));
										fi.close();
									}
								}
							}
                		}catch(Throwable e){
                			t.append("Reading text failed\n");
                			t.append(e.toString());
                		}
                        root.add(t);
                } catch (Throwable e) {
                        e.printStackTrace();
                }
        }
}
