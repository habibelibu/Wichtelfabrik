package io.Wichtelfabrik.Minigames;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class CookieClicker extends Minigame{
    Button cookie;

    public CookieClicker() {
        Drawable d = new TextureRegionDrawable(new TextureRegion(new Texture("cookie.png")));
        cookie = new Button(d);
        cookie.setPosition(250,500);
        addActor(cookie);
        cookie.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("xD!");
            }
        });
    }
}
