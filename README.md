REVISER C TRES NUL
==================

Application that help studying vocabulary.

Introduction
------------

The original idea came from Rémi Badr., that developped a first version in C#, running only on Windows OS.
The present application should not only have the same features as the previous application but must also run on Mac OS.

Run application
---------------

- From Intellij :

```shell
    "C:\Program Files\Java\jdk1.8.0_131\bin\java" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2017.2.1\lib\idea_rt.jar=55769:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2017.2.1\bin" -Dfile.encoding=UTF-8 -classpath "L:\git_clone\reviser-c-tres-nul\target\classes;C:\Users\huzor\.m2\repository\no\tornado\tornadofx\1.7.2\tornadofx-1.7.2.jar;C:\Users\huzor\.m2\repository\org\glassfish\javax.json\1.0.4\javax.json-1.0.4.jar;C:\Users\huzor\.m2\repository\org\jetbrains\kotlin\kotlin-reflect\1.1.1\kotlin-reflect-1.1.1.jar;C:\Users\huzor\.m2\repository\org\jetbrains\kotlin\kotlin-stdlib\1.1.1\kotlin-stdlib-1.1.1.jar;C:\Users\huzor\.m2\repository\org\jetbrains\annotations\13.0\annotations-13.0.jar;C:\Users\huzor\.m2\repository\org\jetbrains\kotlin\kotlin-stdlib-jre8\1.1.1\kotlin-stdlib-jre8-1.1.1.jar;C:\Users\huzor\.m2\repository\org\jetbrains\kotlin\kotlin-stdlib-jre7\1.1.1\kotlin-stdlib-jre7-1.1.1.jar;C:\Program Files\Java\jdk1.8.0_131\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_131\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_131\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_131\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_131\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_131\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_131\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_131\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_131\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_131\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_131\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_131\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_131\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_131\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_131\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_131\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_131\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_131\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_131\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_131\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_131\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_131\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_131\jre\lib\rt.jar" fr.huzor.MyAppKt
```

- choose *csv files* like : `src\main\resources\HSK1.csv`

Requirements
------------
- jdk1.8_111
- kotlin1.1

Sources
-------

Based on [tornadofx](https://github.com/edvin/tornadofx)
