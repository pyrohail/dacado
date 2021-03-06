/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.pyrohail.dacado;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

/**
 * Encodes text into an image.
 * @author Justin Harrison
 * @since 0.1
 */
public final class TextEncoder implements Encodable {
  private final BufferedImage encodedImage;

  /**
   * Constructor.
   * @param text String to be encoded.
   */
  public TextEncoder(String text) {
    while (0 != text.length() % 3) {
      text += null;
    }

    final Dimension dimension = calculateDimension(text.length());
    encodedImage = new BufferedImage(
        (int) dimension.getWidth(),
        (int) dimension.getHeight(),
        BufferedImage.TYPE_INT_RGB
    );

    encodedImage.setRGB(0, 0, Format.TYPE_INT_TEXT);

    int pixelPos = 0;
    for (int i = 0; i < text.length(); i += 3) {
      pixelPos++;
      encodedImage.setRGB(pixelPos, 0, new Color(text.charAt(i), text.charAt(i + 1), text.charAt(i + 2)).getRGB());
    }
  }

  /**
   * Calculates needed rectangular size of encoded image.
   * @param textLength Length of text to be encoded.
   * @return Rectangular size of encoded image.
   */
  private Dimension calculateDimension(final int textLength) {
    final Dimension dimension = new Dimension();
    // Divide by 3 so each RGB value can hold a character.
    // Add one so first pixel can hold data type. e.g. text, or binary
    dimension.setSize((textLength / 3) + 1, 1);
    return dimension;
  }

  @Override
  public BufferedImage getEncodedImage() {
    return encodedImage;
  }
}
