#!/bin/bash
set -xe
TOOLDIR=$(dirname $0)
java -cp $($TOOLDIR/mkClassPath.sh) net.sf.saxon.Transform -s:target/AST.xml\
 -xsl:target/xslt/genApiDoc.xslt
