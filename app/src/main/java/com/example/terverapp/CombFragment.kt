package com.example.terverapp

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.terverapp.databinding.FragmentCombBinding

class CombFragment: Fragment(R.layout.fragment_comb) {
    private val viewBinding: FragmentCombBinding by viewBinding(FragmentCombBinding::bind)
    private var withRep: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        viewBinding.apply {
            parentFragmentManager.beginTransaction()
                .replace(COMB_CONTAINER, PerestFragment())
                .commit()

            btnPerest.setOnClickListener {
                if(withRep) {
                    parentFragmentManager.beginTransaction()
                        .replace(COMB_CONTAINER, PerestRepFragment())
                        .commit()
                }
                else {
                    parentFragmentManager.beginTransaction()
                        .replace(COMB_CONTAINER, PerestFragment())
                        .commit()
                }
            }

            btnSochet.setOnClickListener {
                if(withRep) {
                    parentFragmentManager.beginTransaction()
                        .replace(COMB_CONTAINER, SochetRepFragment())
                        .commit()
                }
                else {
                    parentFragmentManager.beginTransaction()
                        .replace(COMB_CONTAINER, SochetFragment())
                        .commit()
                }
            }

            btnRazmesh.setOnClickListener {
                if (withRep) {
                    parentFragmentManager.beginTransaction()
                        .replace(COMB_CONTAINER, RazmeshRepFragment())
                        .commit()
                }
                else {
                    parentFragmentManager.beginTransaction()
                        .replace(COMB_CONTAINER, RazmeshFragment())
                        .commit()
                }

            }

            btnWithRep.setOnClickListener {
                val newDrawable = when(withRep) {
                    true -> {
                        withRep = false
                        ContextCompat.getDrawable(requireContext(), R.drawable.baseline_radio_button_unchecked_24)
                    }
                    false -> {
                        withRep = true
                        ContextCompat.getDrawable(requireContext(), R.drawable.baseline_radio_button_checked_24)
                    }
                }
                btnWithRep.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    newDrawable,
                    null
                )
            }
        }
    }

    companion object {
        val COMB_CONTAINER = R.id.comb_container
    }
}