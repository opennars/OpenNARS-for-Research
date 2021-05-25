OpenNARS Core
-------------------------
OpenNARS v3.1.x based on OpenNARS v1.5.8 https://github.com/patham9/opennars_declarative_core
It can be considered a new attempt to implement the new architecture which was originally built on top of v3.0.4: https://github.com/opennars/opennars/releases/tag/v3.1.0
For more information, read the release messages.

Source Code
-----------
In nars_core_java/ and nars_gui/ are the NARS core and the Swing GUI in Java. This came out of the code of Pei Wang in nars/ directory.

nars-dist/ contains a NARS distribution (executable, examples, web page with applet), that could be zipped for releases.

The test suite is nars_core/src/test/java/nars/main_nogui/TestReasoning0.java .

Build
-----
There are scripts for Linux and Windows to compile and create the executable jar:
build.sh and build.bat .

Test
----
The unit test suite is here. It ensures non-regression of the reasoner:
nars_core/src/test/java/nars/main_nogui/TestReasoning.java
It works classically: for each  XX-in.txt in directory nars-dist/Examples, it runs NARBatch, and compares actual result with reference result  XX-out.txt.
To create a new test input, add the NARS input as XX-in.txt in nars-dist/Examples , run the test suite, and move result file from temporary directory
/tmp/nars_test/XX-out.txt
into nars-dist/Example .
NOTE:
Due to the sensitivity of results regarding the implementation of the reasonner, it is difficult to write robust tests. But for pure non-regression tests, the test is usable.


Source Code status
------------------
See also http://code.google.com/p/open-nars/wiki/ProjectStatus
Current version has been fully tested for single capability at a time; there may still be bugs when combining capabilities.
Jean-Marc Vanel was working on this roadmap, mainly in GUI and software engineering tasks :
- reestablish a non-regression test suite
- make an independant syntax verifyer based on a grammar parser : it will give the column & line of error (there is a Scala combinator grammar)
- separe NARS in 2 modules with a Maven build : nars_gui and nars_core_java
