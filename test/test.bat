@echo off
setlocal enabledelayedexpansion
echo Running Java Test...

:: Step 1: Ensure bin directory exists
if not exist ..\bin mkdir ..\bin

:: Step 2: Compile all Java files recursively into bin/
echo Compiling Java files...
for /r ..\app\src\main\java %%f in (*.java) do (
    echo Compiling %%f
    javac -cp ..\app\src\main\java -Xlint:none -d ..\bin "%%f"
    if %errorlevel% neq 0 (
        echo Compilation failed!
        exit /b 1
    )
)


:: Step 3: Loop through all .in files in inputs/
echo Running tests...
for %%f in (inputs\*.in) do (
    set "name=%%~nf"  :: Get test name without extension
    set "actual_output=actual\!name!.out"
    set "expected_output=outputs\!name!.out"

    echo Running test: !name!.in
    java -cp ..\bin main.Main < %%f > "!actual_output!"

    echo Comparing output...
    fc /W "!actual_output!" "!expected_output!" > nul
    if !errorlevel! == 0 (
        echo                               TEST PASSED: !name!
    ) else (
        echo                               TEST FAILED: !name!
        echo Check "!actual_output!" for details.
    )
)

endlocal

pause
