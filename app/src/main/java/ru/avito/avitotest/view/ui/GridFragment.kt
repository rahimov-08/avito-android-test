package ru.avito.avitotest.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.avito.avitotest.R
import ru.avito.avitotest.service.model.GridNumber
import ru.avito.avitotest.view.adapter.GridAdapter
import ru.avito.avitotest.view.callback.OnRemoveButtonListener
import ru.avito.avitotest.viewmodel.GridViewModel

class GridFragment : Fragment(), OnRemoveButtonListener {

    private lateinit var gridRecycler: RecyclerView
    private lateinit var gridViewModel: GridViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_grid, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gridRecycler = view.findViewById(R.id.grid_recycler)

        val gridAdapter = GridAdapter(this)
        val layoutManager = GridLayoutManager(context, getSpanCount())
        gridRecycler.apply {
            setLayoutManager(layoutManager)
            adapter = gridAdapter
        }

        gridViewModel = ViewModelProvider(this).get(GridViewModel::class.java)
        gridViewModel.refreshData()
        gridViewModel.numbersLiveData.observe(viewLifecycleOwner) {
            gridAdapter.submitData(it)
        }
    }

    private fun getSpanCount() = context?.resources?.getInteger(R.integer.recyclerSpanCount)?:2

    companion object {
        fun newInstance(): GridFragment {
            return GridFragment()
        }
    }

    override fun removeButtonClicked(model: GridNumber) {
        gridViewModel.removeNumber(model)
    }

}