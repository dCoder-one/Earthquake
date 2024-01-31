package com.example.earthquake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.earthquake.databinding.ActivityEarthquakeListBinding
import retrofit2.Call
import retrofit2.Callback
// import java.time.

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    val baseUrl = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/"

    fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

class EarthquakeListActivity : AppCompatActivity() {

    companion object {
        val TAG = "EarthquakeListActivity"
    }
    private lateinit var binding : ActivityEarthquakeListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEarthquakeListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val retrofit = RetrofitHelper.getInstance()
        val earthquakeService = retrofit.create(EarthquakeService::class.java)
        val earthquakeCall = earthquakeService.getEarthquakeDataPastDay()
        earthquakeCall.enqueue(object : Callback<FeatureCollection> {
            override fun onResponse(
                call: Call<FeatureCollection>,
                response: Response<FeatureCollection>
            ) {
                // this is where you wil get your data.
                // this is where you will set up your adapter for recyclerview
                // don't forget a null check before trying to use the data
                // response.body() contains the object in the <> after Response

                Log.d(TAG, "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<FeatureCollection>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        } )
    }
}