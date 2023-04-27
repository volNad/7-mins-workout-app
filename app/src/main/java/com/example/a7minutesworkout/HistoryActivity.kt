package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.a7minutesworkout.databinding.ActivityHistoryBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {

    private var binding: ActivityHistoryBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarHistoryActivity)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "History"
        }

        binding?.toolbarHistoryActivity?.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val dao = (application as WorkoutApp).db.historyDao()
        getAllCompletedDates(dao)


    }

    private fun getAllCompletedDates(historyDao: HistoryDao) {
        lifecycleScope.launch {
            historyDao.fetchAllDates().collect{ allCompletedDatesList ->
                if (allCompletedDatesList.isNotEmpty()) {
                    binding?.tvCompletedLabel?.visibility = View.VISIBLE
                    binding?.rvItemsHistory?.visibility = View.VISIBLE
                    binding?.tvNoDataLabel?.visibility = View.GONE

                    binding?.rvItemsHistory?.layoutManager = LinearLayoutManager(this@HistoryActivity)

                    val dates = ArrayList<String>()
                    for(date in allCompletedDatesList) {
                        dates.add(date.date)
                    }
                    val historyAdapter = HistoryAdapter(dates)
                    binding?.rvItemsHistory?.adapter = historyAdapter
                } else{
                    binding?.tvCompletedLabel?.visibility = View.GONE
                    binding?.rvItemsHistory?.visibility = View.GONE
                    binding?.tvNoDataLabel?.visibility = View.VISIBLE
                }
            }
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}