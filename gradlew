#!/usr/bin/env sh
# Gradle start up script for UN*X
set -e
DIRNAME=$(cd "$(dirname "$0")" && pwd)
cd "$DIRNAME"

# Try to find java
if [ -n "$JAVA_HOME" ] ; then
    JAVACMD="$JAVA_HOME/bin/java"
else
    JAVACMD="java"
fi

# Download wrapper jar if missing
WRAPPER_JAR="$DIRNAME/gradle/wrapper/gradle-wrapper.jar"
if [ ! -f "$WRAPPER_JAR" ]; then
    echo "Downloading Gradle wrapper..."
    curl -s -o "$WRAPPER_JAR" "https://github.com/gradle/gradle/raw/v8.10.0/gradle/wrapper/gradle-wrapper.jar" 2>/dev/null || \
    wget -q -O "$WRAPPER_JAR" "https://github.com/gradle/gradle/raw/v8.10.0/gradle/wrapper/gradle-wrapper.jar"
fi

exec "$JAVACMD" -classpath "$WRAPPER_JAR" org.gradle.wrapper.GradleWrapperMain "$@"
