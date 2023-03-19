package com.github.brokendust.profession

import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.function.console
import taboolib.common.platform.function.getDataFolder
import taboolib.common.platform.function.releaseResourceFile
import taboolib.module.configuration.Configuration
import taboolib.module.lang.sendLang
import java.io.File

object ProfessionManager {
    private val Professions: MutableMap<String, Profession> = mutableMapOf()

    fun loadProfessionsFromFolder(folder: File) {
        if (!folder.isDirectory) {
            throw IllegalArgumentException("Not a directory")
        }
        val yamlFiles = folder.listFiles { _, name -> name.endsWith(".yml") || name.endsWith(".yaml") }
        yamlFiles?.forEach { file ->
            val professionData = Configuration.loadFromFile(file)
            val Profession = Profession(professionData)
            Professions[Profession.name] = Profession
            console().sendLang("profession-loaded" , file.name , Profession.name)
        }
    }

    fun addProfession(Profession: Profession) {
        if (Professions.containsKey(Profession.name)) {
            console().sendLang("profession-exists" , Profession.name)
        }
        Professions[Profession.name] = Profession
    }

    fun removeProfession(name: String): Profession? {
        return Professions.remove(name)
    }

    fun getProfession(name: String): Profession? {
        return Professions[name]
    }

    fun getAllProfessions(): List<Profession> {
        return Professions.values.toList()
    }

    @Awake(LifeCycle.LOAD)
    fun load(){
        val folder = File(getDataFolder() ,"professions")
        if (!folder.exists()){
            folder.mkdirs()
            releaseResourceFile("professions/example.yml")
        }
        loadProfessionsFromFolder(folder)
    }
}