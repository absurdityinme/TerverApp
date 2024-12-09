package com.example.terverapp

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.terverapp.databinding.FragmentPerestRepBinding

class PerestRepFragment:Fragment(R.layout.fragment_perest_rep) {
    private val viewBinding: FragmentPerestRepBinding by viewBinding(FragmentPerestRepBinding::bind)
}