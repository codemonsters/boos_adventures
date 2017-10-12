# Boo's Adventures #

### Descripción ###

Juego cooperativo en 2D con soporte multijugador mediante bluetooth
* Aventura 2d plataformas-puzzle
* Gameplay que incentivará la cooperación para resolver cada nivel
* Fisicas y graficos por tiles
* Screen 16:9 1920x1080
* Niveles sin scroll / one screener
* Graficos verde monocromo

### Herramientas ###

* Gimp
* Box2D
* LibGDX
* AndroidStudio
* Git / GitKraken

### Preparación del entorno de desarrollo ###

* Instalar JDK 8.0 SE
* Instalar Git y opcionalmente algún frontend gráfico como GitKraken
* Instalar Android Studio
* Clonar el repositorio Git localmente dentro de una nueva carpeta (por ejemplo con Git o GitKraken):
  * Con Git desde la línea de comandos: git clone https://nombre_de_usuario_en_bitbucket@bitbucket.org/Code_Monsters/boos_adventures.git
  * Con GitKraken:
    * Si no lo hemos hecho antes necesitamos dar acceso a GitKraken para acceder a nuestra cuenta de BitBucket:
      * Accedemos a las preferencias de GitKraken (menú file / preferences) y allí "Authentication" y "BitBucket". Pulsamos "Connect" y se abrirá un navegador para permitirnos autorizar a GitKraken para que acceda a nuestra cuenta de BitBucket
    * Pulsar el icono de carpeta de la parte superior izquierda de la ventana principal
    * Seleccionar "clone" y veremos todos los repositorios a los que tenemos acceso desde nuestra cuenta de BitBucket. Desde aquí seleccionaremos el repositorio y la carpeta en la que queremos clonarlo
* En función del IDE que utilicemos:
  * Android Studio:
    * Configurarlo para que conozca la localización del comando"git" Git que tenemos instalado (en Linux en mi caso lo autodetecta bien, en Windows parece que hay que indicarle desde las preferencias del IDE dónde está git.exe)
	* Importar el proyecto Gradle desde Android Studio pulsando "Import proyect" y seleccionando la carpeta "game" que acabamos de clonar
    * En la ventana que indica "Unregistered VCS root detected" pulsamos "add root" para que IntelliJ reconozca nuestro repositorio Git
    * Creamos una nueva configuración de ejecución (run configuration): Edit configurations / pulsamos el "+" / seleccionamos "Application" y rellenamos: name=desktop, Main class=...DesktopLauncher, Working directory: la carpeta assets dentro del proyecto core, Use classpath of mod: seleccionamos el módulo desktop
  * IntelliJ IDEA:
    * Instalar Gradle
    * Importar proyecto: Seleccionar "Import Proyect", seleccionamos la carpeta "game", Import proyect from external Gradle model
	* En la ventana que indica "Unregistered VCS root detected" pulsamos "add root" para que IntelliJ reconozca nuestro repositorio Git
    * Creamos una nueva configuración de ejecución (run configuration): Edit configurations / pulsamos el "+" / seleccionamos "Application" y rellenamos: name=desktop, Main class=...DesktopLauncher, Working directory: la carpeta assets dentro del proyecto core, Use classpath of mod: seleccionamos el módulo desktop
	* En los settings de los proyectos "desktop" y "core" indicamos el JDK a utilizar (ej: 1.8)

### Estructura general del repositorio ###

En la carpeta raiz se encontrará:

* README.md (este archivo)
* game: proyecto LibGDX que contiene el juego principal
* bluetooth_controller: Aplicación móvil que permite conectarse al juego desde un dispositivo móvil y controlar los movimientos mediante su pantalla táctil

### Mecánica de juego ###

* Objetivo de cada nivel: Los jugadores comienzan desde una posición de spawn y tienen que llegar todos vivos a la meta
* Los jugadores pueden saltar (pero poco)
* Hay un limite de tiempo para superar cada nivel
* Los jugadores podrían perder una vida si al caer sufren un golpe grande (o hacer que pierda otro si le caen encima con suficiente fuerza)
* Pero si caes encima de un compañero puede que no mueras pero él sí
* Bonus stages: Modo deathmatch (Vasijas)

### Elementos ###

Elementos que se pueden encontrar formando parte de los distintos niveles:

* Elementos necesarios (en todos los niveles):
  * Posición de spawn
  * Meta
  
* Elementos opcionales (según nivel):
  * Bloques que se hunden con el peso
  * Trampolines.
  * Lava , pinchos

### Brainstorming ###

* random generated zone.
* Quien gane se lleva una corona cosmetica.
* Editor de niveles online.
