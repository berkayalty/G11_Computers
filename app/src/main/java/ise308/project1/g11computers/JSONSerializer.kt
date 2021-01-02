package ise308.project1.g11computers

import android.content.Context
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONTokener
import java.io.*

class JSONSerializer(private val filename: String, private val context: Context) {
    @Throws(IOException::class, JSONException::class)
    fun save(computerList: ArrayList<Computer>) {
        // Make an array in JSON format
        val jsonArray = JSONArray()
        // Load it with the computers
        for (computer in computerList) {
            jsonArray.put(computer.convertTOJSON())
        }
        // Write it to the private disk space of our app
        var writer: Writer? = null
        try{
            val outFile = context.openFileOutput(filename, Context.MODE_PRIVATE)
            writer = OutputStreamWriter(outFile)
            writer.write(jsonArray.toString())
        }finally {
            writer?.close()
        }
    }

    @Throws(IOException::class, JSONException::class)
    fun load(): ArrayList<Computer> {
        val computerList = ArrayList<Computer>()
        var reader: BufferedReader? = null
        try {
            val inFile = context.openFileInput(filename)
            reader = BufferedReader(InputStreamReader(inFile))
            val jsonString = StringBuilder()

            for (line in reader.readLine()) {
                jsonString.append(line)
            }
            val jsonArray = JSONTokener(jsonString.toString()).nextValue() as JSONArray
            for (i in 0 until jsonArray.length()) {
                computerList.add(Computer(jsonArray.getJSONObject(i)))
            }
        }catch(fileNotFound : FileNotFoundException){
            println("File Not Found: $fileNotFound")
        }finally {
            reader?.close()
        }
        return computerList
    }
}