package es.codemonsters.boosadventures.game.objetosdeljuego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.utils.Align;

import es.codemonsters.boosadventures.game.pantallas.PantallaJuego;

public class CabeceraNivel extends TextArea {

    private float tiempoAcumulado = 0;
    private float y = -10;
    public CabeceraNivel(String texto, PantallaJuego pj) {
        super(texto,pj.getUiSkin(),"nobg");

        setPosition(20 ,y);
        setSize(pj.ANCHO_DEL_MUNDO*6, pj.ALTO_DEL_MUNDO*6);
        setAlignment(Align.bottomLeft);
    }
    @Override
    public void act(float delta) {
        super.act(delta);
        tiempoAcumulado += delta;
        Color c = getColor();
        if (tiempoAcumulado < 1) {
            c.a = tiempoAcumulado;
            setColor(c);
        } else if (tiempoAcumulado > 1 && tiempoAcumulado < 4) {
            c.a = 1 - (tiempoAcumulado - 1)  / 3;
            setColor(c);
        }
        setY(getY() - delta * 25);
    }
}
