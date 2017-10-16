# Boo's Adventures #

### Descripción ###

Juego cooperativo en 2D con soporte multijugador mediante bluetooth

* Aventura 2D plataformas-puzzle multijudaro
* Gameplay que incentivará la cooperación para resolver cada nivel
* Control del juego desde aplicación Android con conexión bluetooth y también a través del teclado (útil durante el desarrollo para controlar dos o tres jugadores)
* Fisicas y graficos por tiles
* Pantalla 16:9 1920x1080 (aunque durante el desarrollo por comodidad podemos utilizar 1280x720)
* Niveles sin scroll / one screener
* Graficos verde monocromo

Más información en el [wiki del proyecto](https://bitbucket.org/Code_Monsters/boos_adventures/wiki/)
### Herramientas ###

* LibGDX
* Box2D
* Android Studio
* Git / GitKraken
* Gimp

### Preparación del entorno de desarrollo ###

* Instalar JDK 8.0 SE
* Instalar Git y opcionalmente algún frontend gráfico como GitKraken
* Instalar Android Studio
* Clonar el repositorio Git localmente dentro de una nueva carpeta (por ejemplo con Git o GitKraken):
  * Con Git desde la línea de comandos:
    * git clone https://nombre_de_usuario_en_bitbucket@bitbucket.org/Code_Monsters/boos_adventures.git
  * Con GitKraken:
    * Si no lo hemos hecho antes necesitamos dar acceso a GitKraken para acceder a nuestra cuenta de BitBucket:
      * Accedemos a las preferencias de GitKraken (menú file / preferences) y allí "Authentication" y "BitBucket". Pulsamos "Connect" y se abrirá un navegador para permitirnos autorizar a GitKraken para que acceda a nuestra cuenta de BitBucket
    * Pulsar el icono de carpeta de la parte superior izquierda de la ventana principal
    * Seleccionar "clone" y veremos todos los repositorios a los que tenemos acceso desde nuestra cuenta de BitBucket. Desde aquí seleccionaremos el repositorio y la carpeta en la que queremos clonarlo

Configuración de Android Studio:

* Si es necesario lo configuramos para que conozca la localización del comando "git" (en Linux por defecto parece que lo autodetecta, en Windows podría ser necesario indicarle desde las preferencias del IDE dónde está git.exe)
* Importamos el proyecto Gradle desde Android Studio pulsando "Import project" y seleccionando la carpeta "game" que acabamos de clonar
* En la ventana que indica "Unregistered VCS root detected" pulsamos "add root" para que IntelliJ reconozca nuestro repositorio Git
* Creamos una nueva configuración de ejecución (run configuration): Edit configurations / pulsamos el "+" / seleccionamos "Application" y rellenamos: name=desktop, Main class=...DesktopLauncher, Working directory: la carpeta assets dentro del proyecto android, Use classpath of mod: seleccionamos el módulo desktop

### Estructura general del repositorio ###

En la carpeta raiz se encontrará:

* README.md (este archivo)
* game: proyecto LibGDX que contiene el juego principal
* bluetooth_controller: Aplicación móvil que permite conectarse al juego desde un dispositivo móvil y controlar los movimientos mediante su pantalla táctil
