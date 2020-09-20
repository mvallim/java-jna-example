# Example Java JNA

This chapter will show you how to use JAVA JNA.

## Own Library header

```c
#include <stdio.h>
#include <stdlib.h>

int sum(int n1, int n2);
```

## Own Library implementation V1

```c
#include <stdio.h>
#include <stdlib.h>
#include "functions.h"

int sum(int n1, int n2) {
  return n1+n2;
}
```

## Own Library implementation V2

```c
#include <stdio.h>
#include <stdlib.h>
#include "functions.h"

int sum(int n1, int n2) {
  return (n1+n2) * 20;
}
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

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [GitHub](https://github.com/mvallim/example-java-jna) for versioning. For the versions available, see the [tags on this repository](https://github.com/mvallim/example-java-jna/tags).

## Authors

* **Marcos Vallim** - *Initial work, Development, Test, Documentation* - [mvallim](https://github.com/mvallim)

See also the list of [contributors](CONTRIBUTORS.txt) who participated in this project.

## License

This project is licensed under the Apache License - see the [LICENSE](LICENSE) file for details
