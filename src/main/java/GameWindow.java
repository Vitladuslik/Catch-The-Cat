import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;


public class GameWindow extends JFrame {

    private static GameWindow game_window;
    private static long last_frame_time;
    private static Image background;
    private static Image cat;
    private static Image game_over;
    private static float cat_left = (int) (Math.random() * 1400);
    private static float cat_top = -200;
    private static float cat_v = 200;
    private static int score = 0;

    private static void music(String music_path) {
        Audio end = new Audio();
        end.play_end(music_path);
    }

    public static void main(String[] args) throws IOException {
        String file_path = "src/main/resources/music.wav";
        Audio sound = new Audio();
        sound.play_music(file_path);
        background = ImageIO.read(GameWindow.class.getResourceAsStream("background.png"));
        cat = ImageIO.read(GameWindow.class.getResourceAsStream("cat.png"));
        game_over = ImageIO.read(GameWindow.class.getResourceAsStream("game_over.png"));
        game_window = new GameWindow();
        game_window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game_window.setLocation(-5,0);
        game_window.setSize(1600,900);
        game_window.setResizable(false);
        last_frame_time = System.nanoTime();
        final GameField game_field = new GameField();
        game_field.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                float cat_right = cat_left + cat.getWidth(null);
                float cat_bottom = cat_top + cat.getHeight(null);
                boolean is_cat = x >= cat_left && x <= cat_right && y >= cat_top && y <= cat_bottom;
                if (is_cat){
                    String meow_path = "src/main/resources/meow.wav";
                    Audio meow = new Audio();
                    meow.play_meow(meow_path);
                    cat_top = -200;
                    cat_left = (int) (Math.random() * (game_field.getWidth() - cat.getWidth(null)));
                    cat_v = cat_v + 20;
                    score++;
                    game_window.setTitle("Счет : " + score); //Сделать отображение счета до первого попадания
                }
            }
        });
        game_window.add(game_field);
        game_window.setVisible(true);
    }

    private static void onRepaint(Graphics g){
        long current_time = System.nanoTime();
        float delta_time = (current_time - last_frame_time) * 0.000000001f;
        last_frame_time = current_time;
        cat_top = cat_top + cat_v * delta_time;
        g.drawImage(background, 0, 0, null);
        g.drawImage(cat, (int)cat_left, (int)cat_top, null);
        if (cat_top > game_window.getHeight()) {
            g.drawImage(game_over, 534,302,null);

        }
    }


    private static class GameField extends JPanel{
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
               onRepaint(g);
               if (cat_top < game_window.getHeight()) {
                   repaint();
               }
               else {
                   String music_path = "src/main/resources/end.wav";
                   music(music_path);
               }

           }


        }

    }
