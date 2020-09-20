# Example Java JNA

This chapter will show you how to use JAVA JNA.

> See a brief summary here: https://en.wikipedia.org/wiki/Java_Native_Access

## Own Library

### Header

```c
#include <stdio.h>
#include <stdlib.h>

int sum(int n1, int n2);
```

### Implementation V1

```c
#include <stdio.h>
#include <stdlib.h>
#include "functions.h"

int sum(int n1, int n2) {
  return n1+n2;
}
```

### Implementation V2

```c
#include <stdio.h>
#include <stdlib.h>
#include "functions.h"

int sum(int n1, int n2) {
  return (n1+n2) * 20;
}
```

### Compile shared library

```shell
gcc -c -fPIC functions-v1.c -o lib/v1/functions.o
gcc -c -fPIC functions-v2.c -o lib/v2/functions.o

gcc -shared -o lib/v1/libfunctions.so lib/v1/functions.o
gcc -shared -o lib/v2/libfunctions.so lib/v2/functions.o
```

## Function Mapping

```java
public interface FunctionsLibrary extends Library {

  public int sum(int n1, int n2);

}
```

## Load library

```java
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
```

## Assert test

```java
public class FunctionsNativeTest {

  @Test
  public void testSum() throws IOException {
    final FunctionsNative functionsV1 = new FunctionsNative("lib/v1/libfunctions.so");
    final FunctionsNative functionsV2 = new FunctionsNative("lib/v2/libfunctions.so");

    assertThat(functionsV1.sum(10, 10), equalTo(20));
    assertThat(functionsV2.sum(10, 10), equalTo(400));
  }

}
```

## Example Application

```java
public class Application {

  private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

  public static void main(final String[] args) throws IOException {

    final FunctionsNative functionsV1 = new FunctionsNative("lib/v1/libfunctions.so");
    final FunctionsNative functionsV2 = new FunctionsNative("lib/v2/libfunctions.so");

    LOGGER.info(() -> "Function SUM v1 : " + functionsV1.sum(10, 10));
    LOGGER.info(() -> "Function SUM v2 : " + functionsV2.sum(10, 10));
  }

}
```

Expected output:

```console
Sep 20, 2020 4:26:26 PM br.com.jna.Application main
INFO: Function SUM v1 : 20
Sep 20, 2020 4:26:26 PM br.com.jna.Application main
INFO: Function SUM v2 : 400
```

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [GitHub](https://github.com/mvallim/example-java-jna) for versioning. For the versions available, see the [tags on this repository](https://github.com/mvallim/example-java-jna/tags).

## Authors

* **Marcos Vallim** - *Initial work, Development, Test, Documentation* - [mvallim](https://github.com/mvallim)

See also the list of [contributors](CONTRIBUTORS.txt) who participated in this project.

## License

This project is licensed under the Apache License - see the [LICENSE](LICENSE) file for details
