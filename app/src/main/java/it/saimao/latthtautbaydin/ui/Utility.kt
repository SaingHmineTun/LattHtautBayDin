package it.saimao.latthtautbaydin.ui

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.google.gson.Gson
import it.saimao.latthtautbaydin.R
import it.saimao.latthtautbaydin.data.JsonData
import java.io.BufferedReader
import java.io.InputStreamReader

object Utility {
    private var jsonData: JsonData? = null
    fun getJsonData(context: Context): JsonData {

        if (jsonData == null) {
            val inputStream = context.resources.openRawResource(R.raw.latt_htaut_bay_din)
            val reader = BufferedReader(InputStreamReader(inputStream))
            val jsonString = reader.readText()

            // Parse the JSON string using Gson
            val gson = Gson()
            jsonData = gson.fromJson(jsonString, JsonData::class.java)
        }
        // Access data from the parsed object
        return jsonData!!
    }
}