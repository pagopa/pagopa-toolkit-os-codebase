
@echo off
@REM https://daniel-sc.github.io/bash-shell-to-bat-converter/

SET DIR=resources
SET JAR_FILE=ToolkitPagoPA-1.0-jar-with-dependencies.jar
echo "Copy artifact"
DEL  %JAR_FILE%
COPY  %CD%.\target\%JAR_FILE% %CD%
REM UNKNOWN: {"type":"If","clause":{"type":"CompoundList","commands":[{"type":"Command","name":{"text":"[","type":"Word"},"suffix":[{"text":"-d","type":"Word"},{"text":"\"$DIR\"","expansion":[{"loc":{"start":1,"end":4},"parameter":"DIR","type":"ParameterExpansion"}],"type":"Word"},{"text":"]","type":"Word"}]}]},"then":{"type":"CompoundList","commands":[{"type":"Command","name":{"text":"echo","type":"Word"},"suffix":[{"text":"Configuration already set","type":"Word"}]}]},"else":{"type":"CompoundList","commands":[{"type":"Command","name":{"text":"echo","type":"Word"},"suffix":[{"text":"Extract configuration","type":"Word"}]},{"type":"Command","name":{"text":"jar","type":"Word"},"suffix":[{"text":"xf","type":"Word"},{"text":"${JAR_FILE}","expansion":[{"loc":{"start":0,"end":10},"parameter":"JAR_FILE","type":"ParameterExpansion"}],"type":"Word"},{"text":"resources","type":"Word"}]}]}}
echo "run %CD%.."
java -jar %JAR_FILE%
