package pdf.reader.happiness.presentation.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper
import pdf.reader.happiness.core.InfoModel
import pdf.reader.happiness.data.cache.models.Type
import pdf.reader.happiness.databinding.FragmentStatisticBinding
import pdf.reader.happiness.presentation.adapter.ItemAdapter


class StatisticFragment : Fragment(),ItemAdapter.OnClick {

    private lateinit var binding: FragmentStatisticBinding

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
        setupChartView()
        binding.rv.isNestedScrollingEnabled = false
        val itemAdapter = ItemAdapter(this)
        val list = ArrayList<InfoModel>()
        for (i in 0..20){
            list.add(InfoModel("Самы хоший","",type = Type.DEFAULT,dataType = Type.DEFAULT))
        }
        binding.rv.adapter = itemAdapter
        itemAdapter.update(list)

    }
    private fun setupChartView(){
        binding.chartView.apply {
            isDrawHoleEnabled = true
            setUsePercentValues(true)
            setEntryLabelTextSize(12f)
            setEntryLabelColor(Color.BLACK)
            centerText = "Потраченное время на чтение"
            setCenterTextSize(18f)
            description.isEnabled = false
            legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            legend.orientation = Legend.LegendOrientation.VERTICAL
            legend.setDrawInside(true)
            legend.isEnabled = true
            description.isEnabled = true
        }

        val dataModel = ArrayList<PieEntry>()
        dataModel.add(PieEntry(0.5f, "ЛЮБОВЬ"))
        dataModel.add(PieEntry(0.1f, "ЖИЗНЬ"))
        dataModel.add(PieEntry(0.1f, "УСПЕХ"))
        dataModel.add(PieEntry(0.4f, "СЧАСТЬЕ"))
        val colors = ArrayList<Int>()

        ColorTemplate.MATERIAL_COLORS.forEach {
            colors.add(it)
        }
        ColorTemplate.VORDIPLOM_COLORS.forEach {
            colors.add(it)
        }
        val pie = PieDataSet(dataModel, "LABEL")
        pie.colors = colors

        val pieData = PieData(pie)
        pieData.setDrawValues(true)
        pieData.setValueTextSize(16f)
        pieData.setValueTextColor(Color.BLUE)

        binding.chartView.data = pieData
        binding.chartView.setUsePercentValues(true)
        binding.chartView.invalidate()

    }

    override fun onClick(infoModel: InfoModel) {

    }
}