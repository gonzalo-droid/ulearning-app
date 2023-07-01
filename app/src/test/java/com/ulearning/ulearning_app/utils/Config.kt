package com.ulearning.ulearning_app.utils

import java.io.File

object Config {
    fun readJsonFile(fileName: String): String {
        val classLoader = javaClass.classLoader
        val file: File = File(classLoader.getResource(fileName)?.file ?: "")
        return file.readText()
    }
}
