package com.example.myapplication.internal.base

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.exception.ErrorMessageFactory

abstract class BaseFragment : Fragment() {

    private var dialog: AlertDialog? = null

    protected fun renderError(throwable: Throwable) {
        val message = ErrorMessageFactory.create(throwable)
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    protected fun showLoadingDialog(@StringRes message: Int) {
        dialog = AlertDialog.Builder(requireContext()).create()
        val view = layoutInflater.inflate(R.layout.dialog_loading, null)
        dialog?.setView(view)
        dialog?.setCanceledOnTouchOutside(false)
        view.findViewById<TextView>(R.id.tv_loading_title).text = getString(message)
        dialog?.show()
    }

    protected fun hideLoadingDialog() {
        dialog?.cancel()
    }

    protected fun showToast(@StringRes message: Int) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    protected fun hideKeyboard() {
        val view: View? = requireActivity().currentFocus
        if (view != null) {
            val imm: InputMethodManager? = requireContext().getSystemService(
                Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}