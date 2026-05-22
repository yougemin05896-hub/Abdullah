package com.example.data.media

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class MediaCompressor @Inject constructor(
    private val context: Context
) {
    fun compressImage(file: File): File {
        val bitmap = BitmapFactory.decodeFile(file.absolutePath)
        val compressedFile = File(context.cacheDir, "compressed_${file.name}")
        val fos = FileOutputStream(compressedFile)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos)
        fos.flush()
        fos.close()
        return compressedFile
    }
}
