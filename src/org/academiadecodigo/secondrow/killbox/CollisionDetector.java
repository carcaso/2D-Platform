package org.academiadecodigo.secondrow.killbox;

import org.academiadecodigo.secondrow.killbox.maps.Map;
import org.academiadecodigo.secondrow.killbox.objects.Collidable;
import org.academiadecodigo.secondrow.killbox.objects.Door;
import org.academiadecodigo.secondrow.killbox.objects.Key;
import org.academiadecodigo.secondrow.killbox.objects.Player;
import org.academiadecodigo.secondrow.killbox.objects.enemy.Enemy;
import org.academiadecodigo.secondrow.killbox.objects.platform.JumpBox;
import org.academiadecodigo.secondrow.killbox.objects.platform.Platform;

public class CollisionDetector {

    Map map;
    Player player;

    public CollisionDetector(Map map, Player player) {
        this.player = player;
        this.map = map;
    }

    /**
     * Checks collision with Platforms
     *
     * @return array of "if side is touching platform" top, bottom, right, left.
     */
    public boolean[] checkCollision(Platform[] objects) {
        boolean isLanding = false;
        boolean isBumpingHead = false;
        boolean isBumpingRight = false;
        boolean isBumpingLeft = false;

        for (int i = 0; i < objects.length; i++) {
            //PLayerInformation
            int playerStartX = player.getX();
            int playerStartY = player.getY();
            int playerEndX = player.getX() + Var.PLAYER_WIDTH;
            int playerEndY = player.getY() + Var.PLAYER_HEIGHT;

            //Object information
            int objectStartX = objects[i].getX();
            int objectStartY = objects[i].getY();
            int objectEndX = objects[i].getX() + objects[i].getWidth();
            int objectEndY = objects[i].getY() + objects[i].getHeight();

            if ((playerEndX > objectStartX && playerStartX < objectEndX)) {

                if (playerEndY == objectStartY) {
                    isLanding = true;
                }

                if (playerStartY == objectEndY) {
                    isBumpingHead = true;
                }
            }

            // Checks if player is in between a platform and sees if height is the same.
            if ((playerStartY >= objectStartY && playerStartY <= objectEndY)
                    || (playerEndY >= objectStartY && playerEndY <= objectEndY)) {
                if (playerEndX == objectStartX) {
                    isBumpingRight = true;
                }

                if (playerStartX == objectEndX) {
                    isBumpingLeft = true;
                }
            }

            if (isBumpingHead || isBumpingLeft || isBumpingRight || isLanding) {
                objects[i].performCollision();
            }

        }

        boolean[] ret = {isBumpingHead, isLanding, isBumpingRight, isBumpingLeft};
        return ret;
    }

    /**
     * to check collision with keys and enemies.
     *
     * @param object
     */
    public boolean checkCollision(Collidable object) {

        int playerStartX = player.getX();
        int playerStartY = player.getY();
        int playerEndX = player.getX() + Var.PLAYER_WIDTH;
        int playerEndY = player.getY() + Var.PLAYER_HEIGHT;

        //Object information
        int objectStartX = object.getX();
        int objectStartY = object.getY();
        int objectEndX = object.getX() + object.getWidth();
        int objectEndY = object.getY() + object.getHeight();

        if (
                ((playerStartX >= objectStartX && playerStartX <= objectEndX
                        && playerStartY >= objectStartY && playerStartY <= objectEndY)

                        || (playerEndX >= objectStartX && playerEndX <= objectEndX
                        && playerStartY >= objectStartY && playerStartY <= objectEndY)
                        || (playerStartX >= objectStartX && playerStartX <= objectEndX
                        && playerEndY >= objectStartY && playerEndY <= objectEndY)
                        || (playerEndX >= objectStartX && playerEndX <= objectEndX
                        && playerEndY >= objectStartY && playerEndY <= objectEndY))

                        || ((objectStartX >= playerStartX && objectStartX <= playerEndX
                        && objectStartY >= playerStartY && objectStartY <= playerEndY)

                        || (objectEndX >= playerStartX && objectEndX <= playerEndX
                        && objectStartY >= playerStartY && objectStartY <= playerEndY)
                        || (objectStartX >= playerStartX && objectStartX <= playerEndX
                        && objectEndY >= playerStartY && objectEndY <= playerEndY)
                        || (objectEndX >= playerStartX && objectEndX <= playerEndX
                        && objectEndY >= playerStartY && objectEndY <= playerEndY))

        ) {

            object.performCollision();

            if (object instanceof JumpBox) {
                player.setBoosted(true);
            }
            return true;
        }
        return false;
    }



    /*public void checkCollisionEnemies(Enemy[] enemies) {

        int playerStartX = player.getX();
        int playerStartY = player.getY();
        int playerEndX = player.getX() + Var.PLAYER_WIDTH;
        int playerEndY = player.getY() + Var.PLAYER_HEIGHT;
        boolean youDEAD = false;


        for (int i = 0; i < enemies.length; i++) {


            int enemyStartX = enemies[i].getX();
            int enemyStartY = enemies[i].getY();
            int enemyEndX = enemies[i].getX() + enemies[i].getWidth();
            int enemyEndY = enemies[i].getY() + enemies[i].getHeight();


            if ((playerEndX > enemyStartX && playerStartX < enemyEndX)) {

                if (playerEndY == enemyStartY) {
                    youDEAD = true;
                }

                if (playerStartY == enemyEndY) {
                    youDEAD = true;
                }
            }

            if ((playerStartY >= enemyStartY && playerStartY <= enemyEndY)
                    || (playerEndY >= enemyStartY && playerEndY <= enemyEndY)) {

                if (playerEndX == enemyStartX) {
                    System.out.println("right");
                    youDEAD = true;
                }

                if (playerStartX == enemyEndX) {
                    System.out.println("left");
                    youDEAD = true;
                }

                if (youDEAD) {
                    enemies[i].performCollision();
                }
            }
        }
    }*/

    public void checkCollisions(Collidable object) {

        //Player information
        int playerStartX = player.getX();
        int playerStartY = player.getY();
        int playerEndX = player.getX() + Var.PLAYER_WIDTH;
        int playerEndY = player.getY() + Var.PLAYER_HEIGHT;

        //Object information
        int objectStartX = object.getX();
        int objectStartY = object.getY();
        int objectEndX = object.getX() + object.getWidth();
        int objectEndY = object.getY() + object.getHeight();

        if (object instanceof Door) {

            Door door = (Door) object;

            if (door.isOpen()) {
                if ((playerEndX > objectStartX && playerStartX < objectEndX)) {

                    if (playerEndY == objectStartY) {
                        System.out.println("Collision");
                        object.performCollision();
                    }

                    if (playerStartY == objectEndY) {
                        System.out.println("Collision");
                        object.performCollision();
                    }
                }

                // Checks if player is in between a platform and sees if height is the same.
                if ((playerStartY >= objectStartY && playerStartY <= objectEndY)
                        || (playerEndY >= objectStartY && playerEndY <= objectEndY)) {
                    if (playerEndX == objectStartX) {
                        System.out.println("Collision");
                        object.performCollision();
                    }

                    if (playerStartX == objectEndX) {
                        System.out.println("Collision");
                        object.performCollision();
                    }
                }
            }
        }
    }
}
