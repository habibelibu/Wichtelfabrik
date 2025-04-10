package io.Wichtelfabrik;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Card extends Button {
    private boolean toggled;
    public Card(Drawable img) {
        super(img, img, img);
        toggled = false;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(isPressed() && !toggled ){
            System.out.println("2!!!");
            toggled =true;
        }
        if(!isPressed() && toggled){
            toggled = false;
        }

    }


}
