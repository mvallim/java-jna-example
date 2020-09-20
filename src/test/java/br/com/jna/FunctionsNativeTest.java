package br.com.jna;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Test;

public class FunctionsNativeTest {

  @Test
  public void testSum() throws IOException {
    final FunctionsNative functionsV1 = new FunctionsNative("lib/v1/libfunctions.so");
    final FunctionsNative functionsV2 = new FunctionsNative("lib/v2/libfunctions.so");

    assertThat(functionsV1.sum(10, 10), equalTo(20));
    assertThat(functionsV2.sum(10, 10), equalTo(400));
  }

}
