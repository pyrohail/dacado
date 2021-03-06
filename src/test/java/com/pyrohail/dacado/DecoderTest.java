/*
 *  This Source Code Form is subject to the terms of the Mozilla Public
 *  * License, v. 2.0. If a copy of the MPL was not distributed with this
 *  * file, You can obtain one at http://mozilla.org/MPL/2.0/. 
 */

package com.pyrohail.dacado;

import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

public class DecoderTest {
  private Decoder decoderText;
  private Decoder decoderBinary;
  private static final String DECODED_TEXT = "Just a test.";
  private static final String DECODED_BINARY = "101010111101101110001";
  
  public DecoderTest() {
    try {
      decoderText = new Decoder(getClass().getResource("/image/text.png").toURI().getPath());
      decoderBinary = new Decoder(getClass().getResource("/image/binary.png").toURI().getPath());
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
  }
  
  @Test
  public void testGetText() {
    assertEquals(DECODED_TEXT, decoderText.getText());
    assertEquals(DECODED_BINARY, decoderBinary.getText());
  }
}
