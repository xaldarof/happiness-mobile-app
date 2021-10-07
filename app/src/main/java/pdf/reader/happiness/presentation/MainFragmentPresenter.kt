package pdf.reader.happiness.presentation

import androidx.lifecycle.asLiveData
import pdf.reader.happiness.core.InfoModel
import pdf.reader.happiness.data.core.DataRepository
import pdf.reader.happiness.tools.PercentCalculator


class MainFragmentPresenter(private val view: MyView) {


    fun updateCoreProgress(list: List<InfoModel>) {
        view.updateCoreProgress(PercentCalculator.Base().calculatePercent(list))
    }

    interface MyView {
        fun updateCoreProgress(percent:Float)
    }
}
