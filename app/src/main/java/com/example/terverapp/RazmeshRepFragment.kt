package com.example.terverapp

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.terverapp.databinding.FragmentRazmeshRepBinding
import com.google.android.material.snackbar.Snackbar
import java.math.BigInteger
import java.math.RoundingMode

class RazmeshRepFragment: Fragment(R.layout.fragment_razmesh_rep) {
    private val viewBinding: FragmentRazmeshRepBinding by viewBinding(FragmentRazmeshRepBinding::bind)
    
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
                    }
                    false -> {
                        btnAnswer.isEnabled = true
                        tilN.error = ""
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
                tvAnswer.text = calculate(n = n, k = k).toString()
            }
        }
    }

    private fun calculate(n: Int, k: Int): BigInteger {
        return n.toBigInteger().pow(k)
    }

}