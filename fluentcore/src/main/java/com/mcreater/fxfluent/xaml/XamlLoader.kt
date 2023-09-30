package com.mcreater.fxfluent.xaml

import com.google.gson.Gson
import java.io.IOException
import java.io.InputStreamReader

class XamlLoader {
    companion object {
        @JvmStatic
        @Throws(IOException::class)
        fun loadAll() {
            val map = Gson().fromJson<Map<*, *>>(
                XamlLoader::class.java.getClassLoader().getResourceAsStream("xaml/indexes/resources.json")?.let {
                    InputStreamReader(it)
                }, MutableMap::class.java
            )
            (map["paths"] as List<*>?)!!.forEach {
                XAMLManager.addFileFromClasspath(it as String)
            }
            XAMLManager.parse()
        }
        @JvmStatic
        fun load(path: String?) {
            XAMLManager.addFileFromClasspath(path)
            XAMLManager.parse()
        }
    }
}