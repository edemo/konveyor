#!/bin/bash
set -xe
TOOLDIR=$(dirname $0)
mkdir -p target/generated-site/markdown target/generated-site/resources
java -cp $($TOOLDIR/mkClassPath.sh) io.github.magwas.tooling.Main . >target/AST.xml
$TOOLDIR/makeSourceDocbook.sh . > target/generated-site/resources/apidoc.docbook
pandoc -s -o target/generated-site/markdown/apidoc.md -f docbook target/generated-site/resources/apidoc.docbook
pandoc -s --toc -o target/generated-site/resources/apidoc.pdf -f docbook target/generated-site/resources/apidoc.docbook
cp README target/generated-site/markdown/index.md
