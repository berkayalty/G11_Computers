package ise308.project1.g11computers
import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import java.sql.Timestamp

class Computer{
    companion object{
        const val TAG = "Computer"
    }
    // Only used when created from a JSONObject
    @Throws(JSONException::class)
    constructor(jsonObject: JSONObject){
        ID= jsonObject.getLong("ID")
        brand=jsonObject.getString("brand")
        model=jsonObject.getString("model")
        modelYear=jsonObject.getInt("modelYear")
        processor=jsonObject.getString("processor")
        ramSize=jsonObject.getInt("ramSize")
        screenSizeInc=jsonObject.getDouble("screenSizeInc")
        graphicCard=jsonObject.getString("graphicCard")
        storageCapacity=jsonObject.getInt("storageCapacity")

    }
    constructor(
        brand: String?,
        model: String?,
        modelYear: Int?,
        processor: String?,
        ramSize: Int,
        screenSizeInc: Double,
        graphicCard: String?,
        storageCapacity: Int
    ) {
        this.ID= Timestamp(System.currentTimeMillis()).time
        this.brand = brand
        this.model = model
        this.modelYear = modelYear
        this.processor = processor
        this.ramSize = ramSize
        this.screenSizeInc = screenSizeInc
        this.graphicCard = graphicCard
        this.storageCapacity = storageCapacity
        Log.i(TAG, ":${this.brand} is created.")
    }
    constructor()

    var ID : Long ?= null
    var brand: String ?=null
    var model: String ?=null
    var modelYear : Int ?= null
    var processor : String ?=null
    var ramSize : Int = 0
    var screenSizeInc : Double = 0.0
    var graphicCard : String ?= null
    var storageCapacity : Int = 0

    @Throws(JSONException::class)
    fun convertTOJSON():JSONObject{
        val jsonObject=JSONObject()
        jsonObject.put("ID",ID)
        jsonObject.put("brand",brand)
        jsonObject.put("model",model)
        jsonObject.put("modelYear",modelYear)
        jsonObject.put("processor",processor)
        jsonObject.put("ramSize",ramSize)
        jsonObject.put("screenSizeInc",screenSizeInc)
        jsonObject.put("graphicCard",graphicCard)
        jsonObject.put("storageCapacity",storageCapacity)
        return jsonObject
    }
}