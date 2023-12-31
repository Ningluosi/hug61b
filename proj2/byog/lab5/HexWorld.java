package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);
    
    private static class Position {
        private int x;
        private int y;
        
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(4);
        switch (tileNum) {
            case 0: return Tileset.GRASS;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.SAND;
            case 3: return Tileset.TREE;
            default: return Tileset.MOUNTAIN;
        }
    }
    public static int hexRowWidth(int s, int i) {
        int effectiveI = i;
        if (effectiveI >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }
        return 2 * effectiveI + s;
    }

    public static int hexRowOffset(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }
        return -effectiveI;
    }

    public static void addRow(TETile[][] world, Position p, int width, TETile t) {
        for (int xi = 0; xi < width; xi += 1) {
            int xCoord = p.x + xi;
            int yCoord = p.y;
            world[xCoord][yCoord] = TETile.colorVariant(t, 32, 32, 32, RANDOM);
        }
    }
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {

        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }

        for (int yi = 0; yi < 2 * s; yi += 1) {
            int thisRowY = p.y + yi;

            int xRowStart = p.x + hexRowOffset(s, yi);

            int rowWidth = hexRowWidth(s, yi);

            Position position = new Position(xRowStart, thisRowY);

            addRow(world, position, rowWidth, t);

        }
    }

    public static Position topRightNeighbor(Position p, int s) {
        int x = p.x + 2 * s - 1;
        int y = p.y + s;
        return new Position(x, y);
    }

    public static Position bottomRight(Position p, int s) {
        int x = p.x + 2 * s - 1;
        int y = p.y - s;
        return new Position(x, y);
    }

    public static void drawRandomVerticalHexes(TETile[][] world, Position p, int s, int n) {
        int x = p.x;
        int y = p.y;
        while (n-- > 0) {
            addHexagon(world, new Position(x, y), s, randomTile());
            y += 2 * s;
        }
    }
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        Position startPosition1 = new Position(3, 6);
        drawRandomVerticalHexes(world, startPosition1, 3, 3);

        Position startPosition2 = bottomRight(startPosition1, 3);
        drawRandomVerticalHexes(world, startPosition2, 3, 4);

        Position startPosition3 = bottomRight(startPosition2, 3);
        drawRandomVerticalHexes(world, startPosition3, 3, 5);

        Position startPosition4 = topRightNeighbor(startPosition3, 3);
        drawRandomVerticalHexes(world, startPosition4, 3, 4);

        Position startPosition5 = topRightNeighbor(startPosition4, 3);
        drawRandomVerticalHexes(world, startPosition5, 3, 3);

        ter.renderFrame(world);

    }
}
