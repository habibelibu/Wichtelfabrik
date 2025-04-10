package io.Wichtelfabrik;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.Wichtelfabrik.Minigames.CookieClicker;

import java.util.ArrayList;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends Game {
    private Stage stage;
    // actorGroups saves bgScreenActors (i=0), BossScreenActors(i=1), FieldScreenActors(i=2), MiniGameScreenActors(i=3) and UIScreenActors(i=4) in this order;
    private Group[] actorGroups;
    private ScreenViewport screenViewport;
    private ArrayList<Card> cards;
    Drawable cardImg;
    private Button reRollButton;
    private Skin standardSkin;

    @Override
    public void create() {
        // Important stuff
        screenViewport = new ScreenViewport();
        stage = new Stage(screenViewport);
        Gdx.input.setInputProcessor(stage);

        // Groups
        actorGroups = new Group[4];
        for (int i = 0; i < actorGroups.length; i++) {
            if(i==3){
                actorGroups[i] = new CookieClicker();
                stage.addActor(actorGroups[i]);
                continue;
            }
            actorGroups[i] = new Group();
            actorGroups[i].setColor(Color.BLUE);
            stage.addActor(actorGroups[i]);
        }

        actorGroups[1].moveBy((float) (1920 * 0.8 * 0 / 3), 0);
        actorGroups[2].moveBy((float) (1920 * 0.8 * 1 / 3), 0);
        actorGroups[3].moveBy((float) (1920 * 0.8 * 2 / 3), 0);

        // Cards
        cards = new ArrayList<>();
        cardImg = new TextureRegionDrawable(new TextureRegion(new Texture("card.png")));
        createCards();
        standardSkin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        // other stuff
        reRollButton = new Button(standardSkin);
        reRollButton.addListener(new ClickListener() { // TODO: grr 2 Listener?!
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("xd!");
                reRollCards();
            }
        });
        reRollButton.setPosition(100, 100);
        actorGroups[0].addActor(reRollButton);


    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(dt);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    private void createCards() {
        for (int i = 0; i < 5; i++) {
            Card card = new Card(cardImg);
            cards.add(card);
            card.setSize(card.getWidth() * 0.2f, card.getHeight() * 0.2f);
            actorGroups[2].addActor(card);
            card.setPosition(cards.indexOf(card) * 70, 20);
        }
    }

    private void reRollCards() {
        for (int i = 0; i < cards.size(); i++) {
            actorGroups[2].removeActor(cards.get(i));
        }
        cards.clear();
        createCards();
    }
}
