package pdf.reader.happiness.presentation.fragments

import android.graphics.Color
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pdf.reader.happiness.core.InfoModel
import pdf.reader.happiness.core.Name
import pdf.reader.happiness.databinding.FragmentStatisticBinding
import pdf.reader.happiness.presentation.adapter.ItemAdapter
import pdf.reader.happiness.presentation.adapter.StatisticItemAdapter
import pdf.reader.happiness.vm.StatisticViewModel


@KoinApiExtension
class StatisticFragment : Fragment(),KoinComponent {

    private lateinit var binding: FragmentStatisticBinding
    private val viewModel:StatisticViewModel = get()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatisticBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        OverScrollDecoratorHelper.setUpOverScroll(binding.scroll)
        binding.rv.isNestedScrollingEnabled = false
        val itemAdapter = StatisticItemAdapter()

        binding.rv.adapter = itemAdapter
        CoroutineScope(Dispatchers.Main).launch {
            setupChartView()
            itemAdapter.update(viewModel.fetchStatistic())
        }

    }

    private suspend fun setupChartView(){
        binding.chartView.apply {
            isDrawHoleEnabled = true
            setEntryLabelTextSize(12f)
            setEntryLabelColor(Color.BLACK)
            centerText = "Потраченное время на чтение"
            setCenterTextSize(14f)
            setUsePercentValues(true)
            description.isEnabled = true
            legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            legend.orientation = Legend.LegendOrientation.VERTICAL
            legend.setDrawInside(true)
            legend.isEnabled = true
            description.isEnabled = false
        }

        val dataModel = ArrayList<PieEntry>()
        dataModel.add(PieEntry(if (viewModel.fetchLoveWastedTime()==0f) 1f
        else viewModel.fetchLoveWastedTime(), Name.LOVE))

        dataModel.add(PieEntry(if (viewModel.fetchLifeWastedTime()==0f) 1f
        else viewModel.fetchLifeWastedTime(), Name.LIFE))

        dataModel.add(PieEntry(if (viewModel.fetchSuccessWastedTime()==0f) 1f
        else viewModel.fetchSuccessWastedTime(), Name.SUCCESS))

        dataModel.add(PieEntry(if (viewModel.fetchHappyWastedTime()==0f) 1f
        else viewModel.fetchHappyWastedTime(), Name.HAPPY))

        val colors = ArrayList<Int>()

        ColorTemplate.MATERIAL_COLORS.forEach {
            colors.add(it)
        }
        ColorTemplate.VORDIPLOM_COLORS.forEach {
            colors.add(it)
        }
        val pie = PieDataSet(dataModel, "Информация")
        pie.colors = colors

        val pieData = PieData(pie)
        pieData.setDrawValues(true)
        pieData.setValueTextSize(16f)
        pieData.setValueTextColor(Color.BLACK)

        binding.chartView.data = pieData
        binding.chartView.invalidate()

    }
}