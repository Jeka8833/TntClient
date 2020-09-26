package net.TntClient.mods.hypixel.parser;

public class Info {
    public Player player = new Player();

    public class Player {
        public Stats stats = new Stats();
    }

    public class Stats {
        public Game TNTGames = new Game();
    }

    public class Game {
        public int wins_tntrun;
        public int deaths_tntrun;
        public int winstreak;
        public int coins;
        public int new_tntrun_double_jumps;
    }
}