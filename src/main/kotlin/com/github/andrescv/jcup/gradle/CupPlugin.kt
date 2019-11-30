/*
  MIT License

  Copyright (c) 2019 Andrés Castellanos
*/
package com.github.andrescv.jcup.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

/** Java CUP Gradle Plugin */
class CUPPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.apply(mapOf("plugin" to "java"))
        project.tasks.create("jcup", CupTask::class.java)
        project.tasks.getByName("compileJava").dependsOn(project.tasks.getByName("jcup"))
    }
}
