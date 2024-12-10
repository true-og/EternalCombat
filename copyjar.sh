#!/usr/bin/env bash

VERSION="$1"

# Ensure the target directory exists
mkdir -p build/libs

# Copy and rename the jar, using $VERSION in the final filename
cp eternalcombat-plugin/build/libs/EternalCombat-*.jar "build/libs/EternalCombat-${VERSION}.jar"
