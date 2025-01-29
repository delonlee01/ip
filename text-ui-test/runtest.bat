@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT
if exist data rm -rf data

REM create a list of files in directory
dir /S /B ..\src\main\java\*.java > files.txt

REM compile the code into the bin folder
javac  -cp ..\src\main\java -Xlint:none -d ..\bin @files.txt
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM remove list of files
del files.txt

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin woody.Woody < input.txt > ACTUAL.TXT
java -classpath ..\bin woody.Woody < input-save.txt > ACTUAL-SAVE.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
FC ACTUAL-SAVE.TXT EXPECTED-SAVE.TXT
