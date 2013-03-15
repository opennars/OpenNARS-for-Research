Source Code
-----------
Under the nars/ directory is the code Pei Wang originally moved into the project, which is still the base of his own programming.

Later Joe Geldart started the open-nars version of NARS, which contains many good ideas (many of which are accepted into 1.5), but it isn't fully consistent with Pei's plan, especially about the new layers (7,8,9), so Pei didn't continue on that code base.
nars-dist/ contains a NARS distribution (executable, examples, web page with applet), that could be zipped for releases.

In nars_core/ there is an embryo of a Maven project for pure reasoning non GUI NARS, currently having only the test suite.


Build
-----
There are scripts for Linux and Windows to compile and create the executable jar: build.sh and build.bat .

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
We are updating the wiki and doing some testing and bug-fixing, but there won't be any major change until May.
Next version 1.5.2 has been fully tested for single capability at a time; there may still be bugs when combining capabilities.
Current version 1.5.1 is already outdated; several bugs have been fixed.

Jean-Marc Vanel is working on this mid term roadmap, mainly in GUI and software engineering tasks :

- 4. make an independant syntax verityer based on a grammar parser : it will give the column & line of error (there is a Scala combinator grammar)
- 5. separe NARS in 2 modules with a Maven build : nars_gui and nars_core
- understand the NPE in NAL5 before the test suite was fixed:
	now (march 14) we know that it can be reproduced by changing the ConceptForgettingRate to be the TaskForgettingRate
- work on Matt pattern matching cases
- work on mail pattern matching cases
- other AGI use cases from this list (mainly user assistants) : http://eulergui.svn.sourceforge.net/viewvc/eulergui/trunk/eulergui/html/AGI_use_cases.html
- design to interact with an application: ECA (review the door1 case), or API to query the current state of NARS beliefs
- save and reload the current state of NARS beliefs
- design for explanation of beliefs
- design verbalization for NARS sentences
- test the new N3 to NARS translator; extend NARS to accept URI's with # like http://x.com/y#z as identifiers, and/or the N3 translator could output prefixed URI's
- more non-regression high level test: multistep, etc
- unit tests for Bag, inference rules
- ATTEMPTO ==> NARS translator

Suggestions for NARS GUI
- pull down combobox menu for concepts
- logo for iconification : Narcissus http://imagebin.org/249908
- check box for On/Off, instead of 2 buttons
- activate control-a for output text areas; 
- allow writing in text areas;
- migrate to Swing ? (benefits: more robustness, same look and feel across platforms, tooltips); 
	cons: Aplet will be more difficult to run
- user can change the default frequency and belief for input
