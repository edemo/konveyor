#!/bin/bash
set -xe
for i in $(find src/$1/generators-java -name '*Generator.java'|sed 'sAsrc/'$1'/generators-java/AA;s/.java$//;sA/A.Ag')
do
	echo $i
	java -cp target/classes:target/test-classes:src/main/resources:src/test/resources:$(echo target/dependency/*.jar|sed 's/ /:/g') io.github.magwas.tooling.Generate $i $1
done
