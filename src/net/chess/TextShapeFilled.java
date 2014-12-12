package net.chess;

import android.graphics.Paint.Style;
import android.graphics.*;
import sofia.graphics.*;

// -------------------------------------------------------------------------
/**
 * Allows for the creation of filled text.
 *
 * @author James Taylor <jamestay@vt.edu>
 * @version 2014.12.02
 */
public class TextShapeFilled
    extends TextShape
{

    // ----------------------------------------------------------
    /**
     * Creates a new filled text object.
     *
     * @param text the String of the text to draw on the screen
     * @param x the x-coordinate location of the shape
     * @param y the y-coordinate location of the shape
     */
    public TextShapeFilled(String text, float x, float y)
    {
        super(text, x, y);
    }


    // ----------------------------------------------------------
    /**
     * Overrides the draw method to support drawing text to the canvas.
     */
    @Override
    public void draw(Drawing drawing)
    {
        if (getText() != null)
        {
            Canvas canvas = drawing.getCanvas();

            Paint paint = getPaint();
            paint.setStyle(Style.FILL); // added line

            canvas.drawText(getText(), getBounds().left, getBounds().top
                - getAscent(), paint);
        }
    }

}
