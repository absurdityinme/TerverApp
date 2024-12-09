package com.example.terverapp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.terverapp.databinding.FragmentMainBinding
import java.math.BigInteger

class MainFragment: Fragment(R.layout.fragment_main) {
    private val viewBinding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        viewBinding.apply {
            btnComb.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(MainActivity.mainContainerId, CombFragment())
                    .addToBackStack(null)
                    .commit()
            }

            btnUrn.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(MainActivity.mainContainerId, UrnFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    companion object {
        fun factorial(n: Int): BigInteger {
            var result = BigInteger.ONE
            for (i in 1..n) {
                result = result.multiply(BigInteger.valueOf(i.toLong()))
            }
            return result
        }
    }
}