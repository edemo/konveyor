#!/bin/bash
set -xe
for i in $(find src/main/java -name '*Generator.java'|sed 'sAsrc/main/java/AA;s/.java$//;sA/A.Ag')
do
	echo $i
	java -cp target/classes:target/dependency/konveyor-base-tooling.jar io.github.magwas.tooling.Generate $i
done
