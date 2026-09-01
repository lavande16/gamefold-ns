// SPDX-FileCopyrightText: Copyright 2026 GameFold Project
// SPDX-License-Identifier: GPL-3.0-or-later

package org.yuzu.yuzu_emu.utils

import android.os.Environment
import java.io.File

object GameFoldPackage {
    val root: File
        get() = File(Environment.getExternalStorageDirectory(), "gamefold")

    val systemDirectory: File get() = childDirectory(root, "system") ?: File(root, "system")
    val driversDirectory: File get() = childDirectory(root, "drivers") ?: File(root, "drivers")
    val gamesDirectory: File get() = childDirectory(root, "games") ?: File(root, "games")

    fun childDirectory(parent: File, vararg acceptedNames: String): File? {
        if (!parent.isDirectory) return null
        val names = acceptedNames.map { it.lowercase() }.toSet()
        return parent.listFiles()?.firstOrNull {
            it.isDirectory && it.name.lowercase() in names
        }
    }

    fun filesWithExtensions(parent: File?, extensions: Set<String>): List<File> {
        if (parent?.isDirectory != true) return emptyList()
        return parent.walkTopDown()
            .filter { it.isFile && it.extension.lowercase() in extensions }
            .sortedBy { it.absolutePath.lowercase() }
            .toList()
    }

    fun inventorySignature(root: File = this.root): String {
        if (!root.isDirectory) return "missing"
        val digest = java.security.MessageDigest.getInstance("SHA-256")
        root.walkTopDown()
            .filter { it.isFile && it.name != ".DS_Store" }
            .sortedBy { it.relativeTo(root).path.lowercase() }
            .forEach { file ->
                val line = "${file.relativeTo(root).path}\u0000${file.length()}\u0000${file.lastModified()}\n"
                digest.update(line.toByteArray())
            }
        return digest.digest().joinToString("") { "%02x".format(it) }
    }
}
