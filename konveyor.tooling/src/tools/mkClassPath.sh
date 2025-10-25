#!/bin/bash
set -xe
if [ -x tools/$(basename $0) ]
then
        exec tools/$(basename $0)
fi
if [ ! -e target/dependency ]
then
	mvn initialize 1>&2
fi
echo target/dependency/konveyor.tooling.jar

