package net.TntClient.mods;

public class Language {

    private final String name;
    private final String code;

    public Language(final String name, final String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}