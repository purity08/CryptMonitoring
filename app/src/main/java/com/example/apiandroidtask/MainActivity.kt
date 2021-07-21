package com.example.apiandroidtask

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.example.apiandroidtask.DI.App
import com.example.apiandroidtask.adapter.Adapter
import com.example.apiandroidtask.model.RecyclerData
import com.example.apiandroidtask.singleton.Singleton
import com.example.apiandroidtask.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Adapter.OnItemClickListener {

    private lateinit var recyclerViewAdapter: Adapter
    private var list = arrayListOf<RecyclerData>()

    @Inject
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        App.appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setAdapter()
        initializeUi()
    }

    private fun setAdapter() {
        recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        recyclerViewAdapter = Adapter(this, listOf(), this)
        recycler.adapter = recyclerViewAdapter
    }

    private fun initializeUi() {

        viewModel.recyclerListData()
            .observe(this, Observer {
                if (it != null) {
                    list = it
                    recyclerViewAdapter.setDataList(list)
                }
            })
    }

    override fun onItemClick(position: Int, v: View?) {
        val clickedItem = list[position]
        Singleton.id = clickedItem.id.toString()
        Singleton.name = clickedItem.name.toString()
        Singleton.symbol = clickedItem.symbol.toString()
        if (v != null) {
            v.isClickable = false
        }
        startActivity(Intent(this, AdditionalInfoActivity::class.java))
    }


    override fun onResume() {
        recycler.adapter?.notifyDataSetChanged()
        super.onResume()
    }
}

