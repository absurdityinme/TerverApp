package com.example.terverapp

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.terverapp.MainFragment.Companion.factorial
import com.example.terverapp.databinding.FragmentUrnBinding
import com.google.android.material.snackbar.Snackbar
import java.math.BigDecimal
import java.math.BigInteger
import java.math.MathContext
import java.math.RoundingMode

class UrnFragment: Fragment(R.layout.fragment_urn) {
    private val viewBinding: FragmentUrnBinding by viewBinding(FragmentUrnBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        var n = 0
        var m = 0
        var k = 0
        var r = 0
        var res: BigDecimal
        viewBinding.apply {
            btnRes1.isEnabled = false
            btnRes2.isEnabled = false

            etN.doOnTextChanged { text, _, _, _ ->
                if (text.isNullOrEmpty()) tilN.error = "Введите значение"
                else {
                    tilN.error = ""
                    n = text.toString().toInt()
                }

                btnRes1.isEnabled = tilN.error == null && tilM.error == null && tilK.error == null && k != 0 && m != 0
                btnRes2.isEnabled = tilN.error == null && tilM.error == null && tilK.error == null && tilR.error == null && k != 0 && r != 0 && m != 0
            }
            etM.doOnTextChanged { text, _, _, _ ->
                if (text.isNullOrEmpty()) tilM.error = "Введите значение"
                else {
                    tilM.error = ""
                    m = text.toString().toInt()
                }

                btnRes1.isEnabled = tilN.error == null && tilM.error == null && tilK.error == null && k != 0 && n != 0
                btnRes2.isEnabled = tilN.error == null && tilM.error == null && tilK.error == null && tilR.error == null && k != 0 && n != 0 && r != 0
            }
            etK.doOnTextChanged { text, _, _, _ ->
                if (text.isNullOrEmpty()) tilK.error = "Введите значение"
                else {
                    tilK.error = ""
                    k = text.toString().toInt()
                }

                btnRes1.isEnabled = tilN.error == null && tilM.error == null && tilK.error == null && n != 0 && m != 0
                btnRes2.isEnabled = tilN.error == null && tilM.error == null && tilK.error == null && tilR.error == null && r != 0 && n != 0 && m != 0
            }
            etR.doOnTextChanged { text, _, _, _ ->
                if (text.isNullOrEmpty()) tilR.error = "Введите значение"
                else {
                    tilR.error = ""
                    r = text.toString().toInt()
                }

                btnRes2.isEnabled = tilN.error == null && tilM.error == null && tilK.error == null && tilR.error == null && k != 0 && n != 0 && m != 0
            }

            btnRes1.setOnClickListener {
                if (n <= m) {
                    Snackbar.make(viewBinding.root, "n должно быть больше m", Snackbar.LENGTH_SHORT).show()
                }
                if (k >= m) {
                    Snackbar.make(viewBinding.root, "k должно быть меньше m", Snackbar.LENGTH_SHORT).show()
                }
                if (n > m && k < m) {
                    res = calculateAll(n = n, m = m, k = k)
                    val roundedResult = res.setScale(10, RoundingMode.HALF_UP)
                    tvAnswer.text = roundedResult.toPlainString()
                }
            }

            btnRes2.setOnClickListener {
                if (n <= m) {
                    Snackbar.make(viewBinding.root, "n должно быть больше m", Snackbar.LENGTH_SHORT).show()
                }
                if (k >= m) {
                    Snackbar.make(viewBinding.root, "k должно быть меньше m", Snackbar.LENGTH_SHORT).show()
                }
                if (r >= k) {
                    Snackbar.make(viewBinding.root, "r должно быть меньше k", Snackbar.LENGTH_SHORT).show()
                }
                if ((n-m) <= (k-r)) {
                    Snackbar.make(viewBinding.root, "(n-m) должно быть больше (k-r)", Snackbar.LENGTH_SHORT).show()
                }
                if(n > m && k < m && r < k && (n-m) > (k-r)) {
                    res = calculateWithR(m = m, n = n, k = k, r = r)
                    val roundedResult = res.setScale(10, RoundingMode.HALF_UP)
                    tvAnswer.text = roundedResult.toPlainString()
                }
            }
        }
    }

    private fun calculateAll(n: Int, m: Int, k: Int): BigDecimal {
        val numerator = factorial(m).toBigDecimal().divide(
            factorial(k).toBigDecimal().multiply(factorial(m - k).toBigDecimal()),
            MathContext.DECIMAL128
        )
        val denominator = factorial(n).toBigDecimal().divide(
            factorial(k).toBigDecimal().multiply(factorial(n - k).toBigDecimal()),
            MathContext.DECIMAL128
        )
        return numerator.divide(denominator, MathContext.DECIMAL128)
    }

    private fun calculateWithR(n: Int, m: Int, k: Int, r: Int): BigDecimal {
        val numerator1 = factorial(m).toBigDecimal().divide(
            factorial(r).toBigDecimal().multiply(factorial(m - r).toBigDecimal()),
            MathContext.DECIMAL128
        )
        val numerator2 = factorial(n - m).toBigDecimal().divide(
            factorial(k - r).toBigDecimal().multiply(factorial(n - m - k + r).toBigDecimal()),
            MathContext.DECIMAL128
        )
        val denominator = factorial(n).toBigDecimal().divide(
            factorial(k).toBigDecimal().multiply(factorial(n - k).toBigDecimal()),
            MathContext.DECIMAL128
        )
        return numerator1.multiply(numerator2).divide(denominator, MathContext.DECIMAL128)
    }
}