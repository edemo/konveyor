#!/bin/bash
set -xe
rsync -rv target/site/ $1
