package org.academiadecodigo.secondrow.killbox;

import org.academiadecodigo.secondrow.killbox.maps.Level1;
import org.academiadecodigo.secondrow.killbox.objects.Menu;

public class Main {

    public static void main(String[] args) {
        Game g = new Game();

        synchronized (g) {
            try {
                Menu menu = new Menu();
                menu.drawMenu();

                while (!menu.getStart())   {
                    Thread.sleep(10);
                }

                while (menu.getStart() && !menu.getPaused()) {
                    g.init();
                    Level1 level1 = new Level1();
                    g.start(level1);
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}