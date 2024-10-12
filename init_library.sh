#!/bin/bash

# Compile the LibraryInitializer Java class
javac -d . src/main/java/your/package/path/LibraryInitializer.java

# Run the LibraryInitializer class to create GitHub Actions YAML and run Git commands
java your.package.path.LibraryInitializer
