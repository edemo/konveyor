#!/bin/bash
set -xe
echo $(echo target/dependency/*|sed 's/ /:/g'):target/classes
