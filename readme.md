
initialize the library:

Here's a summary of the necessary steps:
1. Add the library as a submodule to your project.

- `git submodule add https://github.com/Alek0m0m/library.git libs/library`

2. (Shell Script) Compile and Run the library.

- `bash init_library.sh`

2. Compile and Run the library.
   
- `cd libs/library`
- `javac -d . src/main/java/your/package/path/LibraryInitializer.java`
- `java your.package.path.LibraryInitializer`