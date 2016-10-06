package com.el12n.giflib.web;

/**
 * Created by Alvaro De la Cruz on 10/5/2016.
 */
public enum Color {
    AQUA("Aqua", "#59b3b3"),
    BLUE("Blue", "#5976b3"),
    PURPLE("Purple", "#7e59b3"),
    FUCHSIA("Fucshia", "#b35986"),
    ORANGE("Orange", "#b36859"),
    GOLD("Gold", "#b38f59"),
    POMEGRANATE("Pomegranate", "#c0392b");

    private final String name;
    private final String hexCode;

    Color(String name, String hexCode) {
        this.name = name;
        this.hexCode = hexCode;
    }

    public String getName() {
        return name;
    }

    public String getHexCode() {
        return hexCode;
    }
}
