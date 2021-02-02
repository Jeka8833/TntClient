package net.TntClient.mods.hypixel.parser;

public class Info {
    public Player player = new Player();

    public static class Player {
        public Stats stats = new Stats();
    }

    public static class Stats {
        public Game TNTGames = new Game();
    }

    public static class Game {
        public int wins_tntrun;
        public int deaths_tntrun;
        public int winstreak;
        public int coins;
        public int new_tntrun_double_jumps;
    }
}