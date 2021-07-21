package com.example.apiandroidtask


import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import com.example.apiandroidtask.DI.App
import com.example.apiandroidtask.model.IntervalData
import com.example.apiandroidtask.singleton.Singleton
import com.example.apiandroidtask.viewmodel.MainActivityViewModel
import com.squareup.picasso.Picasso
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.helper.StaticLabelsFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

const val ONE_DAY_IN_MILS = 86400000L

class AdditionalInfoActivity : AppCompatActivity() {

    private lateinit var priceList: ArrayList<IntervalData>

    @Inject
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        App.appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_additional_info)


        viewModel.recycler24hListData()
            .observe(this, {
                priceList = it
                val graph = findViewById<GraphView>(R.id.graph)

                val pricesArrayList = arrayListOf<DataPoint>()

                for ((index, crypt) in priceList.withIndex()) {
                    pricesArrayList.add(
                        DataPoint(
                            index.toDouble(),
                            crypt.priceUsd!!.toDouble()
                        )
                    )
                }
                val formate = SimpleDateFormat("HH")

                var l = priceList[0].time?.toLong()!!

                val dateArrayList = arrayListOf<String>()
                for (i in 0..7) {
                    dateArrayList.add(formate.format(l) + ":00")
                    l += 12340800
                }
                val series = LineGraphSeries(pricesArrayList.toTypedArray())
                series.color = Color.BLUE
                graph.background = Color.LTGRAY.toDrawable()
                graph.addSeries(series)

                val staticLablesFormatter = StaticLabelsFormatter(graph)
                staticLablesFormatter.setHorizontalLabels(dateArrayList.toTypedArray())

                graph.gridLabelRenderer.labelFormatter = staticLablesFormatter
                graph.gridLabelRenderer.textSize = 20F
                graph.visibility = View.VISIBLE

                val highPrice = series.highestValueY
                val lowPrice = series.lowestValueY
                val avgPrice = (highPrice + lowPrice) / 2

                val highPriceView = findViewById<TextView>(R.id.highPriceView)
                highPriceView.text = "$${String.format("%.4f", highPrice)}"

                val lowPriceView = findViewById<TextView>(R.id.lowPriceView)
                lowPriceView.text = "$${String.format("%.4f", lowPrice)}"

                val avgPriceView = findViewById<TextView>(R.id.avgPriceView)
                avgPriceView.text = "$${String.format("%.4f", avgPrice)}"
            })

        val dateView = findViewById<TextView>(R.id.dateView)
        val nameView = findViewById<TextView>(R.id.cryptNameView)
        val symbolView = findViewById<ImageView>(R.id.cryptImageView)

        nameView.text = Singleton.name
        val formatter = SimpleDateFormat("dd-MMMM-YYYY")
        dateView.text = formatter.format(Date(System.currentTimeMillis())).replace("-", " ")

        Picasso.get()
            .load("https://static.coincap.io/assets/icons/${Singleton.symbol?.toLowerCase()}@2x.png")
            .noPlaceholder()
            .error(R.drawable.ic_launcher_foreground)
            .fit()
            .into(symbolView)
    }
}