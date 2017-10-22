package es.codemonsters.boosadventures.controlador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;




public class Controles
        implements Screen
{
    String nombre = "";

    BitmapFont font;

    SpriteBatch batch;
    Texture base;
    Texture top;
    Texture boton;
    Texture boton1;
    int primerToqueX;
    int primerToqueY;
    int topX;
    int topY;
    ShapeRenderer shapeRenderer;
    public float distancia;
    public int grados;
    boolean isPressed = false;
    private MyGdxGame game;

    public Controles(MyGdxGame game, String nombre) { this.nombre = nombre;
        this.game = game;
        batch = new SpriteBatch();
        base = new Texture("stickBase.png");
        top = new Texture("stickTop.png");
        boton = new Texture("botonSaltar.png");
        boton1 = new Texture("botonSaltar1.png");
        font = new BitmapFont();
        shapeRenderer = new ShapeRenderer();
    }


    public void show() {}


    public void render(float delta)
    {
        if (isPressed != Gdx.input.isButtonPressed(0))
        {

            primerToqueX = (Gdx.input.getX() - 50);
            primerToqueY = (-Gdx.input.getY() + 420);
            topX = Gdx.input.getX();
            topY = (-Gdx.input.getY() + 420);

            isPressed = Gdx.input.isButtonPressed(0);
        }

        Gdx.gl.glClearColor(Color.GRAY.r / 2.0F, Color.GRAY.b / 2.0F, Color.GRAY.g / 2.0F, 0.0F);
        Gdx.gl.glClear(16384);
        Vector2 pos1 = new Vector2(Gdx.graphics.getWidth() / 2, 0.0F);
        Vector2 pos2 = new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight());
        dibujarLinea(pos1, pos2, 1, Color.BLACK, Color.BLACK);

        batch.begin();
        batch.draw(boton, Gdx.graphics.getWidth() / 1.5F, Gdx.graphics.getHeight() / 2 - Gdx.graphics.getWidth() / 20, Gdx.graphics.getWidth() / 9 * 2, Gdx.graphics.getWidth() / 9);
        if (isPressed)
        {
            if (Gdx.input.getX() < Gdx.graphics.getWidth() / 2)
            {
                if (Vector2.dst(Gdx.input.getX() - 50, -Gdx.input.getY() + 420, primerToqueX, primerToqueY) <= 140.0F)
                {
                    distancia = (Vector2.dst(Gdx.input.getX() - 50, -Gdx.input.getY() + 420, primerToqueX, primerToqueY) / 140.0F);

                    double dx = primerToqueX - topX;double dy = primerToqueY - topY;
                    double angle = Math.atan2(dy, dx);


                    grados = ((int)(180.0D * (3.0D - angle) / 3.0D));

                    font.draw(batch, String.valueOf(grados) + " grados", 10.0F, 20.0F);

                    font.draw(batch, String.valueOf(distancia) + " distancia relativa", 10.0F, 40.0F);


                    topX = (Gdx.input.getX() - 35);
                    topY = (-Gdx.input.getY() + 430);
                }
            }

            if (primerToqueX < Gdx.graphics.getWidth() / 2) {
                batch.draw(base, primerToqueX, primerToqueY, Gdx.graphics.getWidth() / 9, Gdx.graphics.getWidth() / 9);
                batch.draw(top, topX, topY, Gdx.graphics.getWidth() / 10, Gdx.graphics.getWidth() / 10);
            }

            if ((Gdx.input.getX() < Gdx.graphics.getWidth() / 1.5F + Gdx.graphics.getWidth() / 9 * 2) && (Gdx.input.getX() > Gdx.graphics.getWidth() / 1.5F) && (Gdx.input.getY() > Gdx.graphics.getHeight() / 2 - Gdx.graphics.getWidth() / 20) && (Gdx.input.getY() < Gdx.graphics.getHeight() / 2 - Gdx.graphics.getWidth() / 20 + Gdx.graphics.getWidth() / 9)) {
                batch.draw(boton1, Gdx.graphics.getWidth() / 1.5F, Gdx.graphics.getHeight() / 2 - Gdx.graphics.getWidth() / 20, Gdx.graphics.getWidth() / 9 * 2, Gdx.graphics.getWidth() / 9);
            }
        }
        font.draw(batch, "Buenas, " + nombre, 10.0F, Gdx.graphics.getHeight() - 20);
        batch.end();
    }

    void dibujarLinea(Vector2 pos1, Vector2 pos2, int width, Color color1, Color color2) { shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.line(pos1.x, pos1.y, 0.0F, pos2.x, pos2.y, 0.0F, color1, color2);
        Gdx.gl20.glLineWidth(width);
        shapeRenderer.end();
    }




    public void resize(int width, int height) {}




    public void pause() {}



    public void resume() {}



    public void hide() {}



    public void dispose()
    {
        batch.dispose();
        top.dispose();
        base.dispose();
        font.dispose();
    }
}
