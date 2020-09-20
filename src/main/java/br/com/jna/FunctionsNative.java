package br.com.jna;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;

import com.sun.jna.Native;

public class FunctionsNative implements FunctionsLibrary {

  private final FunctionsLibrary functionsNative;

  public FunctionsNative(final String fileName) throws IOException {
    functionsNative = Native.loadLibrary(extratcFile(fileName), FunctionsLibrary.class);
  }

  @Override
  public int sum(final int n1, final int n2) {
    return functionsNative.sum(n1, n2);
  }

  private String extratcFile(final String fileName) throws IOException {
    final InputStream source = FunctionsNative.class.getClassLoader().getResourceAsStream(fileName);
    final File file = File.createTempFile("lib", null);
    FileUtils.copyInputStreamToFile(source, file);
    return file.getAbsolutePath();
  }

}
