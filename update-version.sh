#!/bin/bash

# requires terminal: bash not powershell
# open bash by typing bash in terminal, and write: ./update-version.sh

# Navigate to the script's directory
cd "$(dirname "$0")" || exit

# Navigate to the project root directory
cd "../library" || exit

# Manually set version (uncomment and set the desired version)
# MANUAL_VERSION="2.2.0-SNAPSHOT"

if [ -z "$MANUAL_VERSION" ]; then
    CURRENT_VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)
    BASE_VERSION=$(echo $CURRENT_VERSION | awk -F. '{print $1}')
    MINOR_VERSION=$(echo $CURRENT_VERSION | awk -F. '{print $2}')
    PATCH_VERSION=$(echo $CURRENT_VERSION | awk -F. '{print $3}')

    # Remove leading zeros
    MINOR_VERSION=$((10#$MINOR_VERSION))
    PATCH_VERSION=$((10#$PATCH_VERSION))

    # Increment patch version
    NEW_PATCH_VERSION=$((PATCH_VERSION + 1))

    # Check if patch version needs to reset and increment minor version
    if [ $NEW_PATCH_VERSION -ge 10 ]; then
        NEW_PATCH_VERSION=0
        NEW_MINOR_VERSION=$((MINOR_VERSION + 1))
    else
        NEW_MINOR_VERSION=$MINOR_VERSION
    fi

    NEW_VERSION="$BASE_VERSION.$NEW_MINOR_VERSION.$NEW_PATCH_VERSION"
else
    NEW_VERSION=$MANUAL_VERSION
fi

# Update the version in the pom.xml files
mvn versions:set -DnewVersion=$NEW_VERSION

mvn install -DskipTests