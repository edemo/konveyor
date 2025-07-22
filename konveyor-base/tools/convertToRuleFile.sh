#!/bin/bash
set -e
if [ $# != 1 ]
then
	echo usage: $0 rulesetname
  exit 1
fi
if [ -x tools/mkClassPath.sh ]
then
  CLASSPATH=$(tools/mkClassPath.sh)
else
  CLASSPATH=$(target/tools/mkClassPath.sh)
fi
mkdir -p target/classes
java -cp $CLASSPATH net.sf.saxon.Transform -s:src/main/pmd/$1.appstate\
 -xsl:xslt/workbenchToRules.xslt\
 -o:target/classes/$1.xml\
 +description=$(realpath src/main/pmd/$(basename $1 .xml).description)
