@echo off
javac -d . -classpath junit.jar;..\ TestParser.java
java -classpath .;junit.jar;hamcrest.jar org.junit.runner.JUnitCore TestParser
del *.class
