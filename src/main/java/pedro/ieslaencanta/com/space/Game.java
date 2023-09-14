package pedro.ieslaencanta.com.space;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro
 */
public class Game {

    //dimensiones de un terminal
    private static int COLUMNS = 80;
    private static int ROWS = 24;
    public static final  TextColor BACKGROUND = TextColor.RGB.Factory.fromString("#000000");
    //20 MHz
    private static int frecuency = 2;
    private Terminal terminal;
    private Screen screen;

    private boolean key_left_pressed;
    private boolean key_right_pressed;
    private boolean key_exit;
    private boolean key_shoot;
    
    private Point2D point2D;

    private enum STATES {
        PLAY,
        GAME_OVER
    }
    private STATES state;

    /**
     * Constructor por defecto
     */
    public Game() {
        this.key_left_pressed = false;
        this.key_right_pressed = false;
        this.key_exit = false;
        this.key_shoot = false;

        this.init();
        try {
            this.terminal = new DefaultTerminalFactory().createTerminal();
            this.screen = new TerminalScreen(this.terminal);
            //no se muestra el cursor
            screen.setCursorPosition(null);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void init() {
        this.state = STATES.PLAY;
        this.point2D= new Point2D(COLUMNS/2,ROWS/2);
       
    }

    

    /**
     * inicializar el juego mientra no se pulse la tecla escape
     */
    public void loop() {
        
        try {
            screen.startScreen();
            screen.clear();
            this.terminal.setBackgroundColor(TextColor.ANSI.CYAN);

            while (!this.key_exit) {
                try {
                    //se procesa la entrada
                    this.process_input();
                    //se actualiza el juego
                    this.update();

                    //se pinta
                    this.paint(this.screen);
                    //1000 es un segundo, frecuenca de 10 Hz son 10 veces por segundo
                    //frecuenca de 20 Hz son 20 veces por segundo, una vez cada 0,05 segundos
                    Thread.sleep((1 / Game.frecuency) * 1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //fin del bucle
            screen.stopScreen();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    

    private void paint(Screen s) {
        try {
            TerminalSize terminalSize = s.getTerminalSize();
            //se repinta en negro
            for (int column = 0; column < terminalSize.getColumns(); column++) {
                for (int row = 0; row < terminalSize.getRows(); row++) {
                    s.setCharacter(column, row, new TextCharacter(
                            ' ',
                            TextColor.ANSI.DEFAULT,
                            BACKGROUND));

                }
            }
            if (this.state == STATES.PLAY) {
              this.point2D.paint(s);
            } else if (this.state == STATES.GAME_OVER) {
                this.paintGameOver(s);
            }
            screen.refresh();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void paintGameOver(Screen s) {
        //https://emojicombos.com/game-over-ascii-art
        //Game.BACKGROUND=TextColor.ANSI.BLACK;
        String game_over[] = {
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⡀⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣤⠀⠀⠀⢀⣴⣿⡶⠀⣾⣿⣿⡿⠟⠛⠁",
            "⠀⠀⠀⠀⠀⠀⣀⣀⣄⣀⠀⠀⠀⠀⣶⣶⣦⠀⠀⠀⠀⣼⣿⣿⡇⠀⣠⣿⣿⣿⠇⣸⣿⣿⣧⣤⠀⠀⠀",
            "⠀⠀⢀⣴⣾⣿⡿⠿⠿⠿⠇⠀⠀⣸⣿⣿⣿⡆⠀⠀⢰⣿⣿⣿⣷⣼⣿⣿⣿⡿⢀⣿⣿⡿⠟⠛⠁⠀⠀",
            "⠀⣴⣿⡿⠋⠁⠀⠀⠀⠀⠀⠀⢠⣿⣿⣹⣿⣿⣿⣿⣿⣿⡏⢻⣿⣿⢿⣿⣿⠃⣼⣿⣯⣤⣴⣶⣿⡤⠀",
            "⣼⣿⠏⠀⣀⣠⣤⣶⣾⣷⠄⣰⣿⣿⡿⠿⠻⣿⣯⣸⣿⡿⠀⠀⠀⠁⣾⣿⡏⢠⣿⣿⠿⠛⠋⠉⠀⠀⠀",
            "⣿⣿⠲⢿⣿⣿⣿⣿⡿⠋⢰⣿⣿⠋⠀⠀⠀⢻⣿⣿⣿⠇⠀⠀⠀⠀⠙⠛⠀⠀⠉⠁⠀⠀⠀⠀⠀⠀⠀",
            "⠹⢿⣷⣶⣿⣿⠿⠋⠀⠀⠈⠙⠃⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠈⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣤⣴⣶⣦⣤⡀⠀",
            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡀⠀⠀⠀⠀⠀⠀⠀⣠⡇⢰⣶⣶⣾⡿⠷⣿⣿⣿⡟⠛⣉⣿⣿⣿⠆",
            "⠀⠀⠀⠀⠀⠀⢀⣤⣶⣿⣿⡎⣿⣿⣦⠀⠀⠀⢀⣤⣾⠟⢀⣿⣿⡟⣁⠀⠀⣸⣿⣿⣤⣾⣿⡿⠛⠁⠀",
            "⠀⠀⠀⠀⣠⣾⣿⡿⠛⠉⢿⣦⠘⣿⣿⡆⠀⢠⣾⣿⠋⠀⣼⣿⣿⣿⠿⠷⢠⣿⣿⣿⠿⢻⣿⣧⠀⠀⠀",
            "⠀⠀⠀⣴⣿⣿⠋⠀⠀⠀⢸⣿⣇⢹⣿⣷⣰⣿⣿⠃⠀⢠⣿⣿⢃⣀⣤⣤⣾⣿⡟⠀⠀⠀⢻⣿⣆⠀⠀",
            "⠀⠀⠀⣿⣿⡇⠀⠀⢀⣴⣿⣿⡟⠀⣿⣿⣿⣿⠃⠀⠀⣾⣿⣿⡿⠿⠛⢛⣿⡟⠀⠀⠀⠀⠀⠻⠿⠀⠀",
            "⠀⠀⠀⠹⣿⣿⣶⣾⣿⣿⣿⠟⠁⠀⠸⢿⣿⠇⠀⠀⠀⠛⠛⠁⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀",
            "⠀⠀⠀⠀⠈⠙⠛⠛⠛⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀"};
        for (int i = 0; i < game_over.length; i++) {
            for (int j = 0; j < game_over[i].length(); j++) {
                s.setCharacter(j + 20,
                        i + 4, TextCharacter.fromCharacter(
                                game_over[i].charAt(j)
                        )[0].withForegroundColor(TextColor.ANSI.values()[(int) (Math.random() * TextColor.ANSI.values().length)]));
            }

        }

    }

    /**
     * Borrar el buffer de teclado para evitar saltos en el movimiento
     *
     */
    private void clear_keyboard_input() {
        KeyStroke keyStroke = null;
        do {
            try {
                keyStroke = screen.pollInput();
            } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (keyStroke != null);
    }

    private void process_input() {
        this.key_left_pressed = false;
        this.key_right_pressed = false;
        this.key_shoot = false;
        try {
            //la lectura es no bloqueante
            KeyStroke keyStroke = screen.pollInput();

            if (keyStroke != null) {
                if (keyStroke.getKeyType() == KeyType.Escape) {
                    this.key_exit = true;
                } else if (keyStroke.getKeyType() == KeyType.ArrowLeft) {
                    this.key_left_pressed = true;
                } else if (keyStroke.getKeyType() == KeyType.ArrowRight) {
                    this.key_right_pressed = true;
                } else {
                    KeyType c = keyStroke.getKeyType();

                    // System.out.println(String.format("%2x", (int) c.toString().charAt(0))+" ....");
                    if ((int) c.toString().charAt(0) == 67) {
                        this.key_shoot = true;
                    }
                }
                //se borra el buffer
                //this.clear_keyboard_input();
            }

        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void update() {
        if (this.state == STATES.PLAY) {
            //mover la nave
            if (this.key_left_pressed) {
                this.point2D.addX(-1);
            }
            if (this.key_right_pressed) {
                this.point2D.addX(1);
            }
            //se mueven las balas de la nave
           
            //se dispara si se ha pulsado la tecla
            if (this.key_shoot) {
               this.state=STATES.GAME_OVER;
            }
            //evaluar las colisiones
           
        }
    }

    


    

    private void setKey_left_pressed(boolean key_left_pressed) {
        this.key_left_pressed = key_left_pressed;
    }

    private boolean isKey_right_pressed() {
        return key_right_pressed;
    }

    private void setKey_right_pressed(boolean key_right_pressed) {
        this.key_right_pressed = key_right_pressed;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.loop();

    }

}
