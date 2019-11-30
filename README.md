# jcup-gradle-plugin

Java CUP (Construction of Useful Parsers) Gradle Plugin

## Usage

Using [plugins DSL](https://docs.gradle.org/current/userguide/plugins.html#sec:plugins_block):

```groovy
plugins {
  id "com.github.andrescv.jcup" version "1.0"
}
```

Using [legacy plugin application](https://docs.gradle.org/current/userguide/plugins.html#sec:old_plugin_application):

```groovy
buildscript {
  repositories {
    maven {
      url "https://plugins.gradle.org/m2/"
    }
  }
  dependencies {
    classpath "gradle.plugin.com.github.andrescv:jcup-gradle-plugin:1.0"
  }
}

apply plugin: "com.github.andrescv.jcup"
```

## Configuration

```groovy
jcup {
    // CUP input file (default 'src/main/jcup/parser.cup')
    input = file('path/to/file.cup')
    // CUP -destdir option (default 'buildDir/generated-src/jcup')
    destdir = file('path/to/destdir')
    // CUP -parser option (default 'parser')
    parser = 'MyParser'
    // CUP -symbols option (default 'sym')
    symbols = 'MySymbols'
    // CUP -interface option
    iface = false
    // CUP -expect option
    expect = 0
    // CUP -nonterms option
    nonterms = false
    // CUP -compact_red option
    compact_red = false
    // CUP -nowarn option
    nowarn = false
    // CUP -nosummary option
    nosummary = true
    // CUP -nopositions option
    nopositions = false
    // CUP -locations option
    locations = false
    // CUP -xmlactions
    xmlactions = false
    // CUP -genericlabels option
    genericlabels = false
    // CUP -noscanner option
    noscanner = false
    // CUP -progress option
    progress = false
    // CUP -time option
    time = false
    // CUP -dump_grammar option
    dump_grammar = false
    // CUP -dump_states option
    dump_states = false
    // CUP -dump_tables option
    dump_tables = false
}
```

Meaning of the CUP options:

```sh
-destdir name  specify the destination directory, to store the generated files in
-parser name   specify parser class name
-typearg args  specify type arguments for parser class
-symbols name  specify name for symbol constant class
-interface     put symbols in an interface, rather than a class
-nonterms      put non terminals in symbol constant class
-expect #      number of conflicts expected/allowed
-compact_red   compact tables by defaulting to most frequent reduce
-nowarn        don't warn about useless productions, etc.
-nosummary     don't print the usual summary of parse states, etc.
-nopositions   don't propagate the left and right token position values
-locations     generate handles xleft/xright for symbol positions in actions
-xmlactions    make the generated parser yield its parse tree as XML
-genericlabels automatically generate labels to all symbols in XML mode
-noscanner     don't refer to java_cup.runtime.Scanner
-progress      print messages to indicate progress of the system
-time          print time usage summary
-dump_grammar  produce a human readable dump of the symbols and grammar
-dump_states   produce a dump of parse state machine
-dump_tables   produce a dump of the parse tables
```
