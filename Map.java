package org.uob.a1;

public class Map {
    private char[][] map;
    final private char EMPTY = '.';
    int width = 0;
    int height = 0;
  

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        map = new char[height][width];
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                map[y][x] = EMPTY;
            }
        }
    }

    public void placeRoom(Position pos, char symbol) {
        map[pos.y][pos.x] = symbol;
    }

    public String display() {
        String output = "";
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                    output += map[y][x];
            }
            output += "\n";
        }
        return output;
    }

}