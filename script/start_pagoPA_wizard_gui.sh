#!/bin/bash

DIR="resources"
JAR_FILE="ToolkitPagoPA-1.0-jar-with-dependencies.jar"
echo "Copy artifact"
rm -f ${JAR_FILE}
cp ../target/${JAR_FILE} .
if [ -d "$DIR" ]; then
    echo "Configuration already set"
else
    echo "Extract configuration"
    jar xf ${JAR_FILE} resources
fi

echo "run ..."
java -jar ${JAR_FILE}
