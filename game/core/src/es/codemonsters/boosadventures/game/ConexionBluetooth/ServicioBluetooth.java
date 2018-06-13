package es.codemonsters.boosadventures.game.ConexionBluetooth;

public interface ServicioBluetooth {

    void iniciarServicio();

    void iniciarDiscovery();

    void pararServicio();

    void pararDiscovery();

    void mandarMensaje(String mensaje);

    String recibirMensaje();

}
