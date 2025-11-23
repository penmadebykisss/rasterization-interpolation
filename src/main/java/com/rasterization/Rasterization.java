package com.rasterization;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Rasterization {

    public static void drawRectangle(
            final GraphicsContext graphicsContext,
            final int x, final int y,
            final int width, final int height,
            final Color color) {

        final PixelWriter pixelWriter = graphicsContext.getPixelWriter();

        for (int row = y; row < y + height; ++row) {
            for (int col = x; col < x + width; ++col) {
                pixelWriter.setColor(col, row, color);
            }
        }
    }
    public static void drawOvalWithBbox(
            final GraphicsContext graphicsContext,
            final int xStart, final int yStart,
            final int a, final int b,
            final Color color
    ) {
        PixelWriter pixelWriter = graphicsContext.getPixelWriter();

        int xCenter = xStart + a;
        int yCenter = yStart + b;


        int xEnd = xStart + 2 * a;
        int yEnd = yStart + 2 * b;

        for (int row = yStart; row < yEnd; ++row) {
            for (int col = xStart; col < xEnd; ++col) {

                double dx = col - xCenter;
                double dy = row - yCenter;
                double value = (dx * dx) / (a * a) + (dy * dy) / (b * b);


                if (value < 1) {
                    pixelWriter.setColor(col, row, color);
                }
            }
        }
    }
}