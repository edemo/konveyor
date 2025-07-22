#!/bin/bash
set -xe
if [ -x tools/mkClassPath.sh ]
then
  CLASSPATH=$(tools/mkClassPath.sh)
else
  CLASSPATH=$(.konveyor/tools/mkClassPath.sh)
fi
java -cp $CLASSPATH net.sf.saxon.Transform -s:target/AST.xml\
 -xsl:.konveyor/xslt/genApiDoc.xslt
