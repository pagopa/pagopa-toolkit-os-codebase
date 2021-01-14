#!/bin/bash

echo "Copy artifact ..."
rm -f ToolkitPagoPA-1.0-jar-with-dependencies.jar
cp ../target/ToolkitPagoPA-1.0-jar-with-dependencies.jar .
if [ -d "$DIR" ]; then
    echo "Configuration already set"
else
    echo "Extract configuration ..."
    jar xf ToolkitPagoPA-1.0-jar-with-dependencies.jar resources
fi

echo "run ..."
java -jar ToolkitPagoPA-1.0-jar-with-dependencies.jar
