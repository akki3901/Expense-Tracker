package com.webtomob.expensetracker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.webtomob.expensetracker.R
import com.webtomob.expensetracker.databinding.FragmentHomeBinding
import com.webtomob.expensetracker.room.model.ExpensesData
import com.webtomob.expensetracker.ui.BaseFragment
import com.webtomob.expensetracker.util.setDebounceOnClickListener
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment(), DataAdatptar.OnItemClickListener {

    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var userList: MutableList<ExpensesData.Expenses>
    private lateinit var dataAdatptar: DataAdatptar

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        clickListener()

        userList = ArrayList()
        dataAdatptar = DataAdatptar(userList, this)
        homeBinding.recyclerView.adapter = dataAdatptar
//        homeBinding.recyclerView.addItemDecoration(
//            DividerItemDecoration(
//                requireContext(),
//                LinearLayoutManager.VERTICAL
//            )
//        )
        homeBinding.recyclerView.setHasFixedSize(true)
        viewModel.getExpenses().observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                userList.clear()
                userList.addAll(it)
                userList.reverse()
                dataAdatptar.notifyDataSetChanged()
                homeBinding.homeNoData.visibility = View.GONE
            } else {
                userList.clear()
                dataAdatptar.notifyDataSetChanged()
                homeBinding.homeNoData.visibility = View.VISIBLE
            }
        })
    }

    private fun setupToolbar(){
        //mainFragmentBinding.titleText.text = "New Text"
    }

    private fun clickListener(){
        homeBinding.addButton.setDebounceOnClickListener() {
            safeNavigate(R.id.action_homeFragment_to_addExpenseFragment)
        }
    }

    override fun itemClick(view: View, position: Int) {
        //TODO("Not yet implemented")
        viewModel.deleteId(position)

    }


}