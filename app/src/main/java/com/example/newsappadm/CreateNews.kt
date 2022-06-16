package com.example.newsappadm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.newsappadm.databinding.ActivityCreateNewsBinding
import com.google.firebase.firestore.FirebaseFirestore

class CreateNews : AppCompatActivity() {
  private lateinit var binding: ActivityCreateNewsBinding
  private val db = FirebaseFirestore.getInstance()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    supportActionBar!!.hide()

    binding = ActivityCreateNewsBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.btnPublicarNoticia.setOnClickListener {
      val titulo = binding.editTituloNoticia.text.toString()
      val noticia = binding.editNoticia.text.toString()
      val data = binding.editDataNoticia.text.toString()
      val autor = binding.editAutorNoticia.text.toString()

      if (titulo.isEmpty() || noticia.isEmpty() || data.isEmpty() || autor.isEmpty()) {
        Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
      } else {
        salvarNoticia(titulo, noticia, data, autor)
      }
    }
  }

  private fun salvarNoticia(titulo: String, noticia: String, data: String, autor: String) {
    val mapNoticias = hashMapOf(
      "titulo" to titulo,
      "noticia" to noticia,
      "data" to data,
      "autor" to autor
    )

    db.collection("noticias").add(mapNoticias).addOnCompleteListener { tarefa ->
      if (tarefa.isSuccessful) {
        Toast.makeText(this, "Notícia salva com sucesso", Toast.LENGTH_SHORT).show()
        limparCampos()
      }
    }
  }

  private fun limparCampos() {
    binding.editTituloNoticia.setText("")
    binding.editNoticia.setText("")
    binding.editDataNoticia.setText("")
    binding.editAutorNoticia.setText("")
  }
}
