package com.example.avaliacaocontinuada2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TelaCadastro : AppCompatActivity() {
    var savedInstanceState: Bundle? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.savedInstanceState = savedInstanceState
        setContentView(R.layout.activity_tela_cadastro)
    }
    var id = 1
    fun addCachorro(view: View) {
        print("cadastrado cachorro")
        print(this.savedInstanceState?.get("et_indicadoCriancas"))
        val apiCachorros = ConexaoApiCachorros.criar()
        val tvConsulta:TextView = findViewById(R.id.tv_consulta)
        val editTextRaca:EditText = findViewById(R.id.et_raca)
        val editTextPreco:EditText = findViewById(R.id.et_precoMedio)
        val precoMedio = editTextPreco.text.toString().toDouble()
        val cachorro = Cachorro(id++, editTextRaca.text.toString(), precoMedio, false)
        apiCachorros.post(cachorro).enqueue(object: Callback<Cachorro> {
            override fun onFailure(call: Call<Cachorro>, t: Throwable) {
                Toast.makeText(baseContext, "Erro chamar api", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Cachorro>, response: Response<Cachorro>) {
                Toast.makeText(baseContext, "cadastrado com sucesso", Toast.LENGTH_SHORT).show()
            }

        });

    }
}
