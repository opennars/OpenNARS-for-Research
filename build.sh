# Very simple build script; should work with minimal modification in Windows cmd shell

rm -rf classes
mkdir classes
javac -d classes -Xlint:unchecked nars/*/*.java
echo 'Main-Class: nars.main.NARS' > manifest.txt
jar cvfm NAR.jar manifest.txt -C classes . 

echo 'You can now launch:'
echo 'java -jar NAR.jar &'
echo or
echo 'java -jar NAR.jar nars-dist/Examples/Example-NAL1-in.txt &'
