package com.webtomob.expensetracker.ui.add

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.webtomob.expensetracker.R
import com.webtomob.expensetracker.databinding.FragmentAddExpenseBinding
import com.webtomob.expensetracker.room.ExpensesDatabase
import com.webtomob.expensetracker.room.model.ExpensesData
import com.webtomob.expensetracker.ui.BaseFragment
import com.webtomob.expensetracker.ui.home.HomeFragmentDirections
import com.webtomob.expensetracker.ui.home.HomeViewModel
import com.webtomob.expensetracker.util.DropdownAdapter
import com.webtomob.expensetracker.util.setDebounceOnClickListener
import org.koin.android.ext.android.inject
import timber.log.Timber
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddExpenseFragment : BaseFragment() {

    private lateinit var addExpenseBinding: FragmentAddExpenseBinding

    companion object {
        fun newInstance() = AddExpenseFragment()
    }

    private val viewModel: AddExpenseViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addExpenseBinding = FragmentAddExpenseBinding.inflate(inflater, container, false)
        return addExpenseBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addExpenseBinding.toolbar.setNavigationOnClickListener {
            backNavigation()
        }

        addExpenseBinding.tieTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val newString = s?.replace("[^a-zA-Z ]+".toRegex(), "") ?: ""
                if (newString != s.toString()) {
                    addExpenseBinding.tieTitle.setText(newString)
                    addExpenseBinding.tieTitle.setSelection(newString.length)
                }
            }
        })

        addExpenseBinding.tilTitle.setAfterTextChanged {
            updateContinueButton()
        }

        addExpenseBinding.tilPrice.setAfterTextChanged {
            updateContinueButton()
        }

        addExpenseBinding.addCatInput.setAdapter(
            DropdownAdapter(
                requireContext(), resources.getStringArray(R.array.cat).toList(),
            )
        )

        addExpenseBinding.btnAddSaveClose.setDebounceOnClickListener(){
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            val formattedDate = current.format(formatter)
            viewModel.insertExpenses(
                addExpenseBinding.tieTitle.text.toString(),
                addExpenseBinding.addCatInput.text.toString(),
                addExpenseBinding.tiePrice.text.toString(), formattedDate.toString())

            backNavigation()
        }

        addExpenseBinding.btnAddSaveMore.setDebounceOnClickListener(){
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            val formattedDate = current.format(formatter)
            viewModel.insertExpenses(
                addExpenseBinding.tieTitle.text.toString(),
                addExpenseBinding.addCatInput.text.toString(),
                addExpenseBinding.tiePrice.text.toString(), formattedDate.toString())

            addExpenseBinding.tieTitle.setText("")
            addExpenseBinding.tiePrice.setText("")
            addExpenseBinding.tieTitle.requestFocus()
            Snackbar.make(
                addExpenseBinding.root,
                getString(R.string.add_data_saved),
                Snackbar.LENGTH_LONG
            ).show()

        }

    }

    private fun updateContinueButton() {
        addExpenseBinding.btnAddSaveClose.isEnabled = (addExpenseBinding.tieTitle.text!!.isNotEmpty() &&
                addExpenseBinding.tiePrice.text!!.isNotEmpty() &&
                addExpenseBinding.addCatInput.text.isNotEmpty())

        addExpenseBinding.btnAddSaveMore.isEnabled = (addExpenseBinding.tieTitle.text!!.isNotEmpty() &&
                addExpenseBinding.tiePrice.text!!.isNotEmpty() &&
                addExpenseBinding.addCatInput.text.isNotEmpty())
    }

    private fun TextInputLayout.setAfterTextChanged(callback: (() -> Unit)? = null) {
        this.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                callback?.invoke()
//                if (p0.toString().isNotEmpty() && p0.toString().isNotBlank()) {
//                    callback?.invoke()
//                }
            }
        })
    }

}