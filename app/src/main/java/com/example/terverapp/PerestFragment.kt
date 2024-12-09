package com.example.terverapp

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.terverapp.MainFragment.Companion.factorial
import com.example.terverapp.databinding.FragmentPerestBinding
import java.math.BigInteger

class PerestFragment: Fragment(R.layout.fragment_perest) {
    private val viewBinding: FragmentPerestBinding by viewBinding(FragmentPerestBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        viewBinding.apply {
            btnAnswer.isEnabled = false
            var n: BigInteger? = null
            etN.doOnTextChanged { text, _, _, _ ->
                when(text.isNullOrEmpty()) {
                    true -> {
                        tilN.error = "Введите значение"
                    }
                    false -> {
                        tilN.error = ""
                        n = factorial(text.toString().toInt())
                    }
                }
                btnAnswer.isEnabled = tilN.error == null
            }

            btnAnswer.setOnClickListener {
                tvAnswer.text = n.toString()
            }
        }
    }


}