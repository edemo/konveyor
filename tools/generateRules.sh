#!/bin/bash
set -xe
TOOLDIR=$(dirname $0)
for i in src/main/pmd/*.appstate
do
	$TOOLDIR/convertToRuleFile.sh $(basename $i .appstate)
done
for i in src/main/pmd/*.xml
do
	cp $i target/classes
done

mkdir -p target/classes/xslt target/classes/tools target/xslt
cp -r xslt/* target/classes/xslt
cp -r xslt/* target/xslt
cp tools/* target/classes/tools

