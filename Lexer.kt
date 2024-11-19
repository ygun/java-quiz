import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

/**
 * Этот класс потокобезопасен.
 */
class Lexer {
    private var input: File? = null

    @Synchronized
    fun setFile(f: File) {
        input = f
    }

    @Synchronized
    fun file(): File? {
        return input
    }

    @Throws(IOException::class)
    fun readContent(): String {
        val i = FileInputStream(input)
        val t = StringBuilder()
        var data: Int
        while (i.read().also { data = it } > 0) {
            t.append(data.toChar())
        }
        return t.toString()
    }

    @Throws(IOException::class)
    fun readContentWOUnicode(): String {
        val i = FileInputStream(input)
        val temp = StringBuilder()
        var data: Int
        while (i.read().also { data = it } > 0) {
            if (data < 0x80) {
                temp.append(data.toChar())
            }
        }
        return temp.toString()
    }

    fun saveContent(text: String) {
        val o = FileOutputStream(input)
        try {
            for (char in text) {
                o.write(char.toInt())
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
