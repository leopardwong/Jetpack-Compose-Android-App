package com.example.jetpack_compose_sample_app.helper

import android.content.Context
import com.google.gson.Gson
import java.io.*


class FileStorageHelper(val context: Context) {

    @Throws(IOException::class)
    fun storeData(fileName: String?, fileContents: Any) {
        val data = Gson().toJson(fileContents)
        val file = File(context.getCacheDir(), fileName)
        val outputStream = FileOutputStream(file)
        outputStream.write(data.toByteArray())
        outputStream.close()
    }

    @Throws(IOException::class)
    fun getData(fileName: String?): String {
        val file = File(context.getCacheDir(), fileName)
        val inputStream = FileInputStream(file)
        val bufferedStream = BufferedInputStream(inputStream)
        val outputStream = ByteArrayOutputStream()
        var result = bufferedStream.read()
        while (result != -1) {
            outputStream.write(result)//Gson().toJson(data)
            result = bufferedStream.read()
        }
        inputStream.close()
        return outputStream.toString("UTF-8")
    }
}