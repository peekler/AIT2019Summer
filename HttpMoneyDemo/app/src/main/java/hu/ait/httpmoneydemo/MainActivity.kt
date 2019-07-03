package hu.ait.httpmoneydemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.ait.httpmoneydemo.data.Base
import hu.ait.httpmoneydemo.network.MoneyAPI
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.exchangeratesapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val currencyAPI = retrofit.create(MoneyAPI::class.java)

            btnGetRates.setOnClickListener {
                currencyAPI.getRates("USD").enqueue(
                    object: Callback<Base>{
                        override fun onFailure(call: Call<Base>, t: Throwable) {
                            tvData.text = t.message
                        }
                        override fun onResponse(call: Call<Base>, response: Response<Base>) {
                            val moneyBase = response.body()
                            tvData.text = "HUF: ${moneyBase?.rates?.HUF}"
                        }
                    }
                )

            }
    }
}
