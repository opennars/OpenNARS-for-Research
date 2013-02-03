REM Very simple build script

DEL classes
mkdir classes
javac -d classes -Xlint:unchecked nars\*\*.java
echo 'Main-Class: nars.main.NARS' > manifest.txt
jar cvfm NARS.jar manifest.txt -C classes . 
DEL manifest.txt

echo 'You can now launch:'
echo 'java -jar NARS.jar'
echo or
echo 'java -jar NARS.jar nars-dist/Examples/Example-NAL1-in.txt'
