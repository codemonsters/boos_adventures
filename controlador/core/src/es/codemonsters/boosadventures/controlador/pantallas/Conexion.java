package es.codemonsters.boosadventures.controlador.pantallas;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import com.badlogic.gdx.utils.viewport.FitViewport;

import es.codemonsters.boosadventures.controlador.MyGdxGame;

public class Conexion implements Screen {

    public static final int ANCHO_DEL_MUNDO = 440;   // Ancho del mundo (en metros)
    public static final int ALTO_DEL_MUNDO = 270;    // Alto del mundo (en metros)

    MyGdxGame game;
    private Stage stage;
    private Table table;

    public Conexion(MyGdxGame game)
    {
        this.game = game;
        stage = new Stage(new FitViewport(ANCHO_DEL_MUNDO, ALTO_DEL_MUNDO));
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        table.setDebug(true);

        // Añadimos widgets a la tabla
        //TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        Skin uiSkin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        TextButton button1 = new TextButton("Button 1", uiSkin);
        table.add(button1);

        //TextButton button2 = new TextButton("Button 2", uiSkin);
        //table.add(button2);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
