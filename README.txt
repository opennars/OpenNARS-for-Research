Source Code
-----------
Under the nars/ directory is the code Pei Wang originally moved into the project, which is still the base of his own programming.

Later Joe Geldart started the open-nars version of NARS, which contains many good ideas (many of which are accepted into 1.5), but it isn't fully consistent with Pei's plan, especially about the new layers (7,8,9), so Pei didn't continue on that code base.
nars-dist/ contains a NARS distribution (executable, examples, web page with applet), that could be zipped for releases.


Build
-----
There are scripts for Linux and Windows to compile and create the executable jar: build.sh and build.bat . There is also an Ant script.


Source Code status
------------------
See also http://code.google.com/p/open-nars/wiki/ProjectStatus
Version 1.5.0 hasn't been fully tested. Pei uploaded it before the new semester starts, otherwise he won't have the time to do so. He is updating the wiki and doing some testing and bug-fixing, but won't do any major change until May.

Jean-Marc Vanel is working on this short term roadmap, mainly in GUI and software engineering tasks :
- 2. make a batch main program (it's already possible to enter input file in command line)
- 3. separe code for pure reasoning from GUI in different directories; for this remove dependencies towards packages main and gui from any other package; for this the simplest is applying the Model-View design pattern (aka publish-subscribe): the reasonner (Model) calls abstract listeners.
- 4. make an independant syntax verityer based on a grammar parser : it will give the column & line of error (there is a Scala combinator grammar)
- 3. separe NARS in 2 modules with a Maven build : nars_gui and nars_core
- 4. write unit tests for the reasonner. Due to the sensitivity of results regarding the implementation of the reasonner, it is difficult to write robust tests. But for pure non non-regression tests , it is possible to reuse the in and out files in nars-dist/Examples .
