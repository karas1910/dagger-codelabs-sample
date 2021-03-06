package com.github.outerheavenproject.wanstagram.ui.shiba

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.outerheavenproject.wanstagram.R
import com.github.outerheavenproject.wanstagram.data.Dogs
import com.github.outerheavenproject.wanstagram.ui.AppNavigatorImpl
import com.github.outerheavenproject.wanstagram.ui.DogAdapter
import kotlinx.coroutines.launch

class ShibaFragment : Fragment(),
    ShibaContract.View {
    private lateinit var presenter: ShibaPresenter
    private lateinit var dogAdapter: DogAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.shiba_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        dogAdapter = DogAdapter(navigator = AppNavigatorImpl())
        recycler.layoutManager = GridLayoutManager(context, 2)
        recycler.adapter = dogAdapter

        presenter = ShibaPresenter(view = this)

        lifecycleScope.launch {
            presenter.start()
        }
    }

    override fun updateDogs(dogs: Dogs) {
        dogAdapter.submitList(dogs.urls)
    }
}
