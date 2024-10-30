#!/bin/bash

# Navigate to the script's directory
cd "$(dirname "$0")" || exit

# Run the update-version.sh script to update the version in the pom.xml files
./update-version.sh

# Commit the updated pom.xml files
git add .
git commit -m "Update version to new version"