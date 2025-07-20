#!/bin/bash
if [ ! -e target/lib ]
then
	mvn initialize 1>&2
fi
if [ -a target/lib/konveyor-pmdrules-jar-with-dependencies.jar ]
then
	echo target/lib/konveyor-pmdrules-jar-with-dependencies.jar
else
	if [ ! -e target/classes ]
	then
		mvn compile 1>&2
  fi
	echo $(echo target/lib/*|sed 's/ /:/g'):target/classes
fi
