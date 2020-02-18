package com.acrony.kotlinjsonlist

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.json.responseJson

import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    internal var URL = "https://demonuts.com/Demonuts/JsonTest/Tennis/json_parsing.php"



    private val jsoncode = 1

    private var listView: ListView? = null

    private var playersModelArrayList: ArrayList<Players_Model>? = null

    private var customeAdapter: CustomAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.lvplayers) as ListView


        FetchPlayers()
    }


    private fun FetchPlayers() {

        try {

            Fuel.post(URL, listOf()).responseJson { request, response, result ->
                onTaskCompleted(result.get().content)


            }
        } catch (e: Exception) {
            Toast.makeText(applicationContext,e.toString(), Toast.LENGTH_LONG).show()

        } finally {

        }
    }


    fun onTaskCompleted(response: String) {
        Log.d("responsejson", response)

        playersModelArrayList = getInfo(response)

        customeAdapter = CustomAdapter(this, playersModelArrayList!!)

        listView!!.adapter = customeAdapter
    }

    fun getInfo(response: String): ArrayList<Players_Model> {
        val playersModelArrayList = ArrayList<Players_Model>()
        try {
            val jsonObject = JSONObject(response)
            if (jsonObject.getString("status") == "true") {

                val dataArray = jsonObject.getJSONArray("data")

                for (i in 0 until dataArray.length()) {
                    val playersModel = Players_Model()

                    val dataobj = dataArray.getJSONObject(i)
                    playersModel.setNames(dataobj.getString("name"))

                    playersModel.setCountrys(dataobj.getString("country"))

                    playersModel.setCitys(dataobj.getString("city"))

                    playersModelArrayList.add(playersModel)

                }
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return playersModelArrayList
    }

}