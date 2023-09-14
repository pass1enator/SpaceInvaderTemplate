# DAWInvader
Crear el juego SpaceInvader con la librería Lanterna.
![alt text](https://github.com/pass1enator/DAWInvader/blob/master/spaceinvaders.png)
1. Crear las clases base:
   Point2D: Coordenada en 2D, usada por la nave, las balas, los muros y los enemigos.
   Bullet: Representa una bala, se mueve por la pantalla en vertical.
2. Una vez creada las clases base, crear las siguientes clases:
   - Game: Contiene el bucle del juego, leer la entrada, actualizar y dibujar. Formada por una nave, un vector de muros, un jugador y un vector de enemigos, además gestiona el número de "vidas", al quedarse sin "vidas" el juego termina.
   - Ship: Se mueve de forma horizontal, contiene una lista de balas, se encarga de la gestión de las mismas, además ha de comprobar que las balas de los enemigos no colisionan con esta.
   - Wall: Entre la nave y los enemigos, cada vez que una bala alcanza una defensa, esta se destruye en la parte que ha colisionado.
   - Enemy: Similar a la nave, puede disparar balas, estas pueden impactar en las defensas y en la nave. Los enemigos disparan de forma aleatoria y se mueven por filas, cada fila de enemigos se mueve a una velocidad diferente. Se tiene al menos 3 filas de enemigos de 4 enemigos por fila cada uno. Opcionalmente se pueden tener diferentes diseños cada enemigo.
3 . Añadir animaciones a los enemigos.     

El diagrama de clases simplificado:
![alt text](https://github.com/pass1enator/DAWInvader/blob/master/Main.png)
