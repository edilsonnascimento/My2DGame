package br.org.edn.my2dgame.main;

public class Constants {
    public static final String UP = "up";
    public static final String LEFT = "left";
    public static final String DOWN = "down";
    public static final String RIGHT = "right";
    public static final String ANY = "any";
    public static final int NOT_OBJECTS = 999;
    public static final int TWO_FPS = 120;
    public static final int TYPE_PLAYER = 0;
    public static final int TYPE_NPC = 1;
    public static final int TYPE_MONSTER = 2;
    public static final int FIVE_FRAMES = 5;
    public static final int TWENTY_FIVE_FRAMES = 25;
    public static final int ONE_SECOND = 600;

    public static boolean isMonster(int type) {
        return type == TYPE_MONSTER;
    }
}
