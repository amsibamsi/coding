# problems
- lwjgl: must extract native libs to cwd when starting app, jars with native libraries for all platforms are pulled with leiningen/maven
- uberjar does not support: native libs, jvm args
- dependencies: lwjgl native libs have wrong folder layout for leiningen
- leiningen dependencies introduce problems, why not just use dependency source code as submodule in git?
- native libs: must be load from filesystem (problem with uberjar), extracting at runtime or using custom classloader is complicated, setting java.library.path is meant to be set outside app, uberjar gets complicated to realize
