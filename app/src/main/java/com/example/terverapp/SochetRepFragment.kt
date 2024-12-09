package com.example.terverapp

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.terverapp.MainFragment.Companion.factorial
import com.example.terverapp.databinding.FragmentSochetRepBinding
import com.google.android.material.snackbar.Snackbar
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

class SochetRepFragment: Fragment(R.layout.fragment_sochet_rep) {
    private val viewBinding: FragmentSochetRepBinding by viewBinding(FragmentSochetRepBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        viewBinding.apply {
            btnAnswer.isEnabled = false

            var n = 0
            var k = 0

            etN.doOnTextChanged { text, _, _, _ ->
                when(text.isNullOrEmpty()) {
                    true -> {
                        tilN.error = "Введите значение"
                        btnAnswer.isEnabled = false
                    }
                    false -> {
                        tilN.error = ""
                        btnAnswer.isEnabled = true
                        n = text.toString().toInt()
                    }
                }
                btnAnswer.isEnabled = tilN.error == null && tilK.error == null && k != 0
            }

            etK.doOnTextChanged {text, _, _, _ ->
                when(text.isNullOrEmpty()) {
                    true -> {
                        tilK.error = "Введите значение"
                    }
                    false -> {
                        tilK.error = ""
                        k = text.toString().toInt()
                    }
                }
                btnAnswer.isEnabled = tilN.error == null && tilK.error == null && n != 0
            }

            btnAnswer.setOnClickListener {
                if (n <= 1) {
                    Snackbar.make(viewBinding.root, "n должно быть больше 1", Snackbar.LENGTH_SHORT).show()
                }
                else {
                    tvAnswer.text = calculate(n = n, k = k).setScale(1, RoundingMode.HALF_UP)
                        .toPlainString()
                }
            }
        }
    }

    private fun calculate(n: Int, k: Int): BigDecimal {
        return factorial(k + n - 1).toBigDecimal().divide(
            factorial(n - 1).toBigDecimal().multiply(factorial(k).toBigDecimal()),
            MathContext.DECIMAL128
        )
    }
}