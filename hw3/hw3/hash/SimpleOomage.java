package hw3.hash;

import java.awt.Color;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdDraw;


public class SimpleOomage implements Oomage {
    private static final double WIDTH = 0.01;
    //    private static final boolean USE_PERFECT_HASH = false;
    private static final boolean USE_PERFECT_HASH = true;
    protected int red;
    protected int green;
    protected int blue;

    public SimpleOomage(int r, int g, int b) {
        if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
            throw new IllegalArgumentException();
        }
        if ((r % 5 != 0) || (g % 5 != 0) || (b % 5 != 0)) {
            throw new IllegalArgumentException("red/green/blue values must all be multiples of 5!");
        }
        red = r;
        green = g;
        blue = b;
    }

    public static SimpleOomage randomSimpleOomage() {
        int red = StdRandom.uniform(0, 51) * 5;
        int green = StdRandom.uniform(0, 51) * 5;
        int blue = StdRandom.uniform(0, 51) * 5;
        return new SimpleOomage(red, green, blue);
    }

    public static void main(String[] args) {
        System.out.println("Drawing 4 random simple Oomages.");
        randomSimpleOomage().draw(0.25, 0.25, 1);
        randomSimpleOomage().draw(0.75, 0.75, 1);
        randomSimpleOomage().draw(0.25, 0.75, 1);
        randomSimpleOomage().draw(0.75, 0.25, 1);
    }

    /**
     * TASK1: Compares object color
     *
     * @param o object
     * @return {@code true}: if this.color equals o.color
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;                  // 1. Not-equal-to-null: x.equals(null) should be false.
        }
        if (this == o) {
            return true;                   // 2. Reflexive: x.equals(x) must be true.
        }
        if (o.getClass() != this.getClass()) {
            return false;                  // 3. verifying that the Object is a SimpleOomage.
        }
        SimpleOomage object = (SimpleOomage) o; // 4.casting
        return (this.red == object.red) && (this.green == object.green) && (this.blue == object.blue);
    }

    /**
     * TASK2: Write a perfect hash function for Simple Oomages.
     * <p>
     * NOTE: "Note that it is generally necessary to override the hashCodemethod
     * whenever the equals method is overridden,
     * so as to maintain the general contract for the hashCode method,
     * which states that equal objects must have equal hash codes.”
     */
    @Override
    public int hashCode() {
        if (!USE_PERFECT_HASH) { // false
            return red + green + blue;
        } else {  // true
            // perfect hashCode
            return (red * 52 * 52 + green * 52 + blue) / 5;
        }
    }

    @Override
    public void draw(double x, double y, double scalingFactor) {
        StdDraw.setPenColor(new Color(red, green, blue));
        StdDraw.filledSquare(x, y, WIDTH * scalingFactor);
    }

    public String toString() {
        return "R: " + red + ", G: " + green + ", B: " + blue;
    }
} 
