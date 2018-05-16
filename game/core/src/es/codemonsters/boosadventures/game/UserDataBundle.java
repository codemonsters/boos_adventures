package es.codemonsters.boosadventures.game;

import es.codemonsters.boosadventures.game.objetosdeljuego.ObjetoDelJuego;

public class UserDataBundle {
    public String texto;
    public ObjetoDelJuego objetoDelJuego;

    public UserDataBundle (String texto, ObjetoDelJuego objetoDelJuego){
        this.texto = texto;
        this.objetoDelJuego = objetoDelJuego;
    }
}
