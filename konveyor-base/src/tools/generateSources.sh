#!/bin/bash
set -xe
for i in $(find src/generators-java -name '*Generator.java'|sed 'sAsrc/generators-java/AA;s/.java$//;sA/A.Ag')
do
	echo $i
	java -cp target/classes:src/main/resources:target/dependency/konveyor-base-tooling.jar:$(echo ../*/target/*.jar ../*.target/target/dependency/*.jar|sed 's/ /:/g') io.github.magwas.tooling.Generate $i
done
