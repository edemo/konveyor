#!/bin/bash
if [ -x tools/mkClassPath.sh ]
then
	CLASSPATH=$(tools/mkClassPath.sh)
else
	CLASSPATH=$(target/tools/mkClassPath.sh)
fi
java -cp $CLASSPATH io.github.magwas.hooking.Main . >target/AST.xml
