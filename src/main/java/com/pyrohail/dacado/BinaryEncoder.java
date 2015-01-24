package com.pyrohail.dacado;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

/**
 * @author Justin Harrison
 * @since 0.1
 */
public final class BinaryEncoder implements Encodable {
    private BufferedImage encodedImage;

    public BinaryEncoder(String binary) {
        while (0 != binary.length() % 3) {
            binary += null;
        }

        final Dimension dimension = calculateDimension(binary);
        encodedImage = new BufferedImage(
                (int) dimension.getWidth(),
                (int) dimension.getHeight(),
                BufferedImage.TYPE_INT_RGB
        );

        encodedImage.setRGB(0, 0, Format.TYPE_INT_Binary);

        int pixelPos = 0;
        for (int i = 0; i < binary.length(); i += 3) {
            pixelPos++;
            encodedImage.setRGB(pixelPos, 0, new Color(binary.charAt(i),
                                                       binary.charAt(i + 1),
                                                       binary.charAt(i + 2)).getRGB());
        }
    }

    private Dimension calculateDimension(final String binary) {
        final Dimension dimension = new Dimension();
        // Add one so first pixel can hold data type. e.g. text, or binary
        dimension.setSize(binary.length() + 1, 1);
        return dimension;
    }

    @Override
    public BufferedImage getEncodedImage() {
        return encodedImage;
    }
}
