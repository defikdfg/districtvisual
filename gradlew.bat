@rem Gradle startup script for Windows
@if "%DEBUG%"=="" @echo off
@rem Set local scope for the variables with windows NT shell
@setlocal
set DIRNAME=%~dp0
set APP_BASE_NAME=%~n0

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome
set JAVA_EXE=java.exe
goto init

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

:init
@rem Download wrapper jar if missing
if not exist "%DIRNAME%\gradle\wrapper\gradle-wrapper.jar" (
    echo Downloading Gradle wrapper...
    powershell -Command "Invoke-WebRequest -Uri 'https://github.com/gradle/gradle/raw/v8.10.0/gradle/wrapper/gradle-wrapper.jar' -OutFile '%DIRNAME%\gradle\wrapper\gradle-wrapper.jar'"
)

:execute
"%JAVA_EXE%" -classpath "%DIRNAME%\gradle\wrapper\gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain %*

:end
@endlocal
