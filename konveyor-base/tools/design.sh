#!/bin/bash
set -xe
TOOLDIR=$(dirname $0)
if [ $# != 1 ]
then
	echo usage: $0 rulesetname
  exit 1
fi
xmlname=src/main/pmd/$1.appstate
if [ ! -f $xmlname ]
then
  echo rule file $xmlname does not exist
  exit 1
fi
cp $xmlname ~/.pmd/rule-designer/appstate.xml
echo "do not forget to save the workbench before exit! (press enter when read)"
read
export JAVAFX_HOME=/usr/share/openjfx
/opt/pmd-bin-7.15.0/bin/pmd designer
cp ~/.pmd/rule-designer/appstate.xml $xmlname
$TOOLDIR/convertToRuleFile.sh $1
cat target/classes/$1.xml

