package com.example.herewegoagain;

public class tools {

    /**
     * @param x
     * @param h
     * @return
     * Metoda zkontroluje zda String h obshauje znak "x".
     * Pokud ano projede metoda sadu podmínek a pokud jediná z nich projde vrátí program hodnotu double.
     * Pokud ne projede program jinou sadu podmínek a vrátí jiné hodnoty.
     * Pokud metoda neprojde ani v jedné podmínce vrátí nulu a skončí.
     */
    public double decode(int x, String h) {
        if (h.equals("x")) {
            if (x == 0) {
                return 7;
            } else if (x == 1) {
                return 81;
            } else if (x == 2) {
                return 154.5;
            } else if (x == 3) {
                return 227.5;
            }
        } else {
            if (x == 0) {
                return 7;
            } else if (x == 1) {
                return 80;
            } else if (x == 2) {
                return 154.5;
            } else if (x == 3) {
                return 227.5;
            }
        }
        return 0;
    }
}
