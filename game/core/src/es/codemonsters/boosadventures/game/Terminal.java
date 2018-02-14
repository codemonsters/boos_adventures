package es.codemonsters.boosadventures.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Terminal extends com.badlogic.gdx.scenes.scene2d.ui.TextArea{

    public Terminal(Skin skin) {
        super("", skin, "nobg");
    }
    public void agregarLinea(String linea){
        setText(getText() + linea + "\n");
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (getLines() * this.getStyle().font.getLineHeight() > getHeight()){
            this.getFirstLineShowing();
            int index = getText().indexOf("\n");
            setText(getText().substring(index+1));
        }
    }
}
