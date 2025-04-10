package io.Wichtelfabrik;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class Card extends Button {
    private boolean toggled;
    private long time = System.currentTimeMillis(); // only for debug

    public Card(Drawable img) {
        super(img, img, img);
        toggled = false;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(isPressed() && !toggled ){
            System.out.println(time);
            toggled =true;
        }
        if(!isPressed() && toggled){
            toggled = false;
        }

    }


}
