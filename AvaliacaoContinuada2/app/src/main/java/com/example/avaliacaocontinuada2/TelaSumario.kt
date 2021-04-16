package com.example.avaliacaocontinuada2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TelaSumario : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_sumario)

        val layoutLista: LinearLayout = findViewById(R.id.layout_lista)

        val apiCachorros = ConexaoApiCachorros.criar()

        apiCachorros.get().enqueue(object : Callback<List<Cachorro>>{
            override fun onFailure(call: Call<List<Cachorro>>, t: Throwable) {
                Toast.makeText(baseContext, "Erro na chamada: ${t.message!!}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<List<Cachorro>>,
                response: Response<List<Cachorro>>
            ) {
                var totalIndicadoCrianca = 0
                var totalNaoIndicadoCrianca = 0
               response.body()?.forEach {



                   if(it.indicadoCriancas) totalIndicadoCrianca++
                   else totalNaoIndicadoCrianca++


               }
               val tvCachorro = TextView(baseContext)
               tvCachorro.text = "Total Cachorros: ${totalIndicadoCrianca + totalNaoIndicadoCrianca} \n Cachorros indicados para crianças: ${totalIndicadoCrianca}\n - Cachorros não indicados para crianças:: ${totalNaoIndicadoCrianca}"
               tvCachorro.setTextColor(Color.parseColor("#9911AA"))
                // adicionando a nova TextView no LinearLayout
               layoutLista.addView(tvCachorro)
            }

        })
    }
}