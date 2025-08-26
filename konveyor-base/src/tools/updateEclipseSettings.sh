#!/bin/bash
set -xe
modules=$(mvn help:evaluate -Dexpression=project.modules -q -DforceStdout |\
	grep "<string>"|\
  sed 's/^[^>]*>//;s/<[^<]*//')

for i in $modules .
do
	(cd $i
	pwd
	cp .konveyor/settings/* .settings)
done
