package io.Wichtelfabrik;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

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
        screenViewport = new ScreenViewport();
        stage = new Stage(screenViewport);
        Gdx.input.setInputProcessor(stage);
        actorGroups = new Group[4];
        for (int i = 0; i < actorGroups.length; i++) {
            actorGroups[i] = new Group();
            stage.addActor(actorGroups[i]);
        }

        cards = new ArrayList<>();
        cardImg = new TextureRegionDrawable(new TextureRegion(new Texture("Card.png")));
        createCards();
        standardSkin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        reRollButton = new Button(standardSkin);
        reRollButton.addListener(new ClickListener() {
            private void clicked() {
                reRollCards();
            }
        });
        reRollButton.setPosition(100, 100);
        stage.addActor(reRollButton);
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
        Card card = new Card(cardImg);
        cards.add(card);
        card.setSize(card.getWidth() * 0.2f, card.getHeight() * 0.2f);
        actorGroups[2].addActor(card);
        card.setPosition(200, 200);
    }

    private void reRollCards() {
        for (int i = 0; i < cards.size(); i++) {
            actorGroups[2].removeActor(cards.get(i));
        }
        createCards();
    }
}
