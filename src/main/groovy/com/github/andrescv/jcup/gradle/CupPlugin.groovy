/*
  MIT License

  Copyright (c) 2019 Andrés Castellanos
*/
package com.github.andrescv.jcup.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.SourceSetContainer

/** Java CUP Gradle Plugin */
class CUPPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.with {
            apply plugin: 'java'
            tasks.create(name: 'jcup', type: CupTask)
            tasks.compileJava.dependsOn tasks.jcup
            sourceSets.main.java.srcDirs += tasks.jcup.destdir
        }
    }
}
