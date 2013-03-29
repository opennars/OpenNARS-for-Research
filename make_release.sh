build.sh
cp NARS.jar nars-dist
rm -r nars-dist/javadoc/*
javadoc -d nars-dist/javadoc nars_core_java/**/*.java nars_gui/**/*.java
zip -r NARS.zip nars-dist
