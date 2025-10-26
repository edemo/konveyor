#!/bin/bash
set -xe
grep mutation target/pit-reports/mutations.xml |grep -v KILLED|sed 's/^.*status=''/status: /;s/umberOfTestsRun.*<mutatedClass>//;s/..mutatedClass.*<lineNumber>/:/;s/..lineNumber>.*<description>/ /;s/..description.*$//'
