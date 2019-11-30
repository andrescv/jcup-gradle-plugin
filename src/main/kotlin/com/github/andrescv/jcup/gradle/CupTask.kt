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
open class CupTask : DefaultTask() {

    @OutputDirectory
    @Optional
    /** generated directory */
    var destdir = project.file("${project.buildDir}/generated-src/jcup")

    @InputFile
    @Optional
    /** input directory */
    var input = project.file("src/main/jcup/parser.cup")

    @Input
    /** specify parser class name [default "parser"] */
    var parser = "parser"

    @Input
    /** specify name for symbol constant class [default "sym"] */
    var symbols = "sym"

    @Input
    /** number of conflicts expected/allowed [default 0] */
    var expect = 0

    @Input
    /** put symbols in an interface, rather than a class */
    var iface = false

    @Input
    /** put non terminals in symbol constant class */
    var nonterms = false

    @Input
    /** compact tables by defaulting to most frequent reduce */
    var compact_red = false

    @Input
    /** don't warn about useless productions, etc. */
    var nowarn = false

    @Input
    /** don't print the usual summary of parse states, etc. */
    var nosummary = true

    @Input
    /** don't propagate the left and right token position values */
    var nopositions = false

    @Input
    /** generate handles xleft/xright for symbol positions in actions */
    var locations = false

    @Input
    /** make the generated parser yield its parse tree as XML */
    var xmlactions = false

    @Input
    /** automatically generate labels to all symbols in XML mode */
    var genericlabels = false

    @Input
    /** don't refer to java_cup.runtime.Scanner */
    var noscanner = false

    @Input
    /** print messages to indicate progress of the system */
    var progress = false

    @Input
    /** print time usage summary */
    var time = false

    @Input
    /** produce a human readable dump of the symbols and grammar */
    var dump_grammar = false

    @Input
    /** produce a dump of parse state machine */
    var dump_states = false

    @Input
    /** produce a dump of the parse tables */
    var dump_tables = false

    @TaskAction
    /** Generates parser. */
    fun javaCup() {
        // delete old files
        project.delete(destdir)
        project.mkdir(destdir)
        // build args
        val args = arrayListOf<String>()
        args.add("-destdir")
        args.add(destdir.absolutePath)
        args.add("-parser")
        args.add(parser)
        args.add("-symbols")
        args.add(symbols)
        if (expect >= 0) {
            args.add("-expect")
            args.add(expect.toString())
        } else {
            logger.warn("expect option should be >= 0")
        }
        if (iface) args.add("-interface")
        if (nonterms) args.add("-nonterms")
        if (compact_red) args.add("-compact_red")
        if (nowarn) args.add("-nowarn")
        if (nosummary) args.add("-nosummary")
        if (nopositions) args.add("-nopositions")
        if (locations) args.add("-locations")
        if (xmlactions) args.add("-xmlactions")
        if (genericlabels) args.add("-genericlabels")
        if (noscanner) args.add("-noscanner")
        if (progress) args.add("-progress")
        if (time) args.add("-time")
        if (dump_grammar) args.add("-dump_grammar")
        if (dump_states) args.add("-dump_states")
        if (dump_tables) args.add("-dump_tables")
        args.add(input.absolutePath)
        // call jcup
        Main.main(args.toTypedArray())
    }
}
