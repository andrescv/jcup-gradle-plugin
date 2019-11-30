/*
  MIT License

  Copyright (c) 2019 Andrés Castellanos
*/
package com.github.andrescv.jcup.gradle

import java_cup.Main
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

/** Java CUP Gradle task. */
class CupTask extends DefaultTask {

    @OutputDirectory
    @Optional
    /** generated directory */
    File destdir = project.file("$project.buildDir/generated-src/jcup")

    @InputFile
    @Optional
    /** input directory */
    File input = project.file('src/main/jcup/parser.cup')

    @Input
    /** specify parser class name [default 'parser'] */
    String parser = 'parser'

    @Input
    /** specify name for symbol constant class [default 'sym'] */
    String symbols = 'sym'

    @Input
    /** number of conflicts expected/allowed [default 0] */
    int expect = 0

    @Input
    /** put symbols in an interface, rather than a class */
    boolean iface = false

    @Input
    /** put non terminals in symbol constant class */
    boolean nonterms = false

    @Input
    /** compact tables by defaulting to most frequent reduce */
    boolean compact_red = false

    @Input
    /** don't warn about useless productions, etc. */
    boolean nowarn = false

    @Input
    /** don't print the usual summary of parse states, etc. */
    boolean nosummary = true

    @Input
    /** don't propagate the left and right token position values */
    boolean nopositions = false

    @Input
    /** generate handles xleft/xright for symbol positions in actions */
    boolean locations = false

    @Input
    /** make the generated parser yield its parse tree as XML */
    boolean xmlactions = false

    @Input
    /** automatically generate labels to all symbols in XML mode */
    boolean genericlabels = false

    @Input
    /** don't refer to java_cup.runtime.Scanner */
    boolean noscanner = false

    @Input
    /** print messages to indicate progress of the system */
    boolean progress = false

    @Input
    /** print time usage summary */
    boolean time = false

    @Input
    /** produce a human readable dump of the symbols and grammar */
    boolean dump_grammar = false

    @Input
    /** produce a dump of parse state machine */
    boolean dump_states = false

    @Input
    /** produce a dump of the parse tables */
    boolean dump_tables = false

    @TaskAction
    /** Generates parser. */
    void javaCup() throws Exception {
        // // delete old files
        project.delete(destdir)
        project.mkdir(destdir)
        // // build args
        def args = []
        args << '-destdir' << destdir.absolutePath
        args << '-parser' << parser
        args << '-symbols' << symbols
        args << '-expect' << expect.toString()
        if (iface) args << '-interface'
        if (nonterms) args << '-nonterms'
        if (compact_red) args << '-compact_red'
        if (nowarn) args << '-nowarn'
        if (nosummary) args << '-nosummary'
        if (nopositions) args << '-nopositions'
        if (locations) args << '-locations'
        if (xmlactions) args << '-xmlactions'
        if (genericlabels) args << '-genericlabels'
        if (noscanner) args << '-noscanner'
        if (progress) args << '-progress'
        if (time) args << '-time'
        if (dump_grammar) args << '-dump_grammar'
        if (dump_states) args << '-dump_states'
        if (dump_tables) args << '-dump_tables'
        args << input.absolutePath
        // call jcup
        Main.main(args as String[])
    }
}
