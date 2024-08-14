package com.ahmadrenhoran.part2_7chucknorris.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmadrenhoran.part2_7chucknorris.R
import com.ahmadrenhoran.part2_7chucknorris.data.model.Response
import com.ahmadrenhoran.part2_7chucknorris.data.repository.JokeRepository
import com.ahmadrenhoran.part2_7chucknorris.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: JokeViewModel by viewModels()
    private lateinit var adapter: JokeAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

        adapter = JokeAdapter()

        val recyclerView = binding.recyclerView
        val buttonSearch = binding.buttonSearch
        val editTextSearch = binding.editTextSearch



        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        buttonSearch.setOnClickListener {
            val query = editTextSearch.text.toString()

            Log.d("aku", "onCreate: $query")


            viewModel.searchJokes(query)
        }

        viewModel.jokes.observe(this) { response ->
            when (response) {
                is Response.Loading -> {
                    Log.d("MALARIA", "onCreate: LOADING")
                }

                is Response.Success -> {
                    Log.d("MALARIA", "onCreate: success")
                    response.data?.let { jokes ->
                        adapter.submitList(jokes.result)
                    }
                }

                is Response.Failure -> {
                    Log.d("MALARIA", "onCreate: FAIL")
                }
            }
        }
    }
}

