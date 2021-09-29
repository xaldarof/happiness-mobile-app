package pdf.reader.happiness.presentation

import pdf.reader.happiness.data.models.InfoModel

class MainFragmentPresenter(private val view: MyView) {

    fun updatePercentLife(list: List<InfoModel>) {
        view.updateLifePercent(calculatePercent(list))
    }

    fun updatePercentHappy(list: List<InfoModel>) {
        view.updateHappyPercent(calculatePercent(list))
    }

    fun updatePercentLove(list: List<InfoModel>) {
        view.updateLovePercent(calculatePercent(list))
    }

    fun updatePercentSuccess(list: List<InfoModel>) {
        view.updateSuccessPercent(calculatePercent(list))
    }

    private fun calculatePercent(list: List<InfoModel>) : Double {
        var count = 0
        list.forEach {
            if (it.finished) {
                count++
            }
        }
        val percent = 100 * (count.toFloat() / list.size.toFloat())

        return percent.toDouble()
    }


    interface MyView {
        fun updateLifePercent(percent: Double)
        fun updateSuccessPercent(percent: Double)
        fun updateHappyPercent(percent: Double)
        fun updateLovePercent(percent: Double)
    }
}