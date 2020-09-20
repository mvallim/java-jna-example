package br.com.jna;

import java.io.IOException;
import java.util.logging.Logger;

public class Application {

  private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

  public static void main(final String[] args) throws IOException {

    final FunctionsNative functionsV1 = new FunctionsNative("lib/v1/libfunctions.so");
    final FunctionsNative functionsV2 = new FunctionsNative("lib/v2/libfunctions.so");

    LOGGER.info(() -> "Function SUM v1 : " + functionsV1.sum(10, 10));
    LOGGER.info(() -> "Function SUM v2 : " + functionsV2.sum(10, 10));
  }

}
