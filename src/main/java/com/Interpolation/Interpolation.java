package com.Interpolation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Interpolation {
    public static void interpolateOval(
            final GraphicsContext graphicsContext,
            final Color startColor, final Color endColor,
            final int xStart, final int yStart,
            final int a, final int b
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
                double value = (dx * dx) /(a * a) + (dy * dy) /(b * b);

                if (value < 1.0) {
                    double distance = Math.sqrt(value);
                    Color interpolatedColor = interpolateColor(startColor, endColor, distance);

                    pixelWriter.setColor(col, row, interpolatedColor);
                }
            }
        }
    }

    public static Color interpolateColor(
            final Color startColor, final Color endColor, final double distance
    ) {
        

        double red = startColor.getRed() * (1 - distance) + endColor.getRed() * distance;
        double green = startColor.getGreen() * (1 - distance) + endColor.getGreen() * distance;
        double blue = startColor.getBlue() * (1 - distance) + endColor.getBlue() * distance;

        return new Color(red, green, blue, 1.0);
    }
}