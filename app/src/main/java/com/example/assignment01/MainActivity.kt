package com.example.assignment01

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.app_name)

        // Ensure state restoration if passed via Bundle
        if (savedInstanceState != null) {
            val savedAmount = savedInstanceState.getString(MainViewModel.KEY_INPUT_AMOUNT)
            if (!savedAmount.isNullOrEmpty() && viewModel.inputAmount.value.isNullOrEmpty()) {
                viewModel.restoreAmount(savedAmount)
            }
        }

        setupObservers()
        setupKeypadListeners()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(MainViewModel.KEY_INPUT_AMOUNT, viewModel.inputAmount.value.orEmpty())
    }

    private fun setupObservers() {
        viewModel.inputAmount.observe(this) { amount ->
            if (amount.isEmpty()) {
                binding.tvTakaDisplay.text = getString(R.string.taka_label_empty)
            } else {
                binding.tvTakaDisplay.text = getString(R.string.taka_label_format, amount)
            }
        }

        viewModel.noteCounts.observe(this) { counts ->
            binding.tvNote500.text = getString(R.string.note_format, 500, counts[500] ?: 0)
            binding.tvNote100.text = getString(R.string.note_format, 100, counts[100] ?: 0)
            binding.tvNote50.text = getString(R.string.note_format, 50, counts[50] ?: 0)
            binding.tvNote20.text = getString(R.string.note_format, 20, counts[20] ?: 0)
            binding.tvNote10.text = getString(R.string.note_format, 10, counts[10] ?: 0)
            binding.tvNote5.text = getString(R.string.note_format, 5, counts[5] ?: 0)
            binding.tvNote2.text = getString(R.string.note_format, 2, counts[2] ?: 0)
            binding.tvNote1.text = getString(R.string.note_format, 1, counts[1] ?: 0)
        }
    }

    private fun setupKeypadListeners() {
        binding.btn0.setOnClickListener { viewModel.onDigitClicked("0") }
        binding.btn1.setOnClickListener { viewModel.onDigitClicked("1") }
        binding.btn2.setOnClickListener { viewModel.onDigitClicked("2") }
        binding.btn3.setOnClickListener { viewModel.onDigitClicked("3") }
        binding.btn4.setOnClickListener { viewModel.onDigitClicked("4") }
        binding.btn5.setOnClickListener { viewModel.onDigitClicked("5") }
        binding.btn6.setOnClickListener { viewModel.onDigitClicked("6") }
        binding.btn7.setOnClickListener { viewModel.onDigitClicked("7") }
        binding.btn8.setOnClickListener { viewModel.onDigitClicked("8") }
        binding.btn9.setOnClickListener { viewModel.onDigitClicked("9") }
        binding.btnClear.setOnClickListener { viewModel.onClearClicked() }
    }
}
