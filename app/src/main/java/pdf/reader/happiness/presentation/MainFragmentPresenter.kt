package pdf.reader.happiness.presentation

import pdf.reader.happiness.data.models.InfoModel

class MainFragmentPresenter(private val view: MyView) {

    fun updatePercentLife(list: List<InfoModel>) {
        var count = 0
        list.forEach {
            if (it.finished) {
                count++
            }
        }
        val percent = 100 * (count.toFloat() / list.size.toFloat())
        view.updateLifePercent(percent.toDouble())
    }

    fun updatePercentHappy(list: List<InfoModel>) {
        var count = 0
        list.forEach {
            if (it.finished) {
                count++
            }
        }
        val percent = 100 * (count.toFloat() / list.size.toFloat())
        view.updateHappyPercent(percent.toDouble())
    }

    fun updatePercentLove(list: List<InfoModel>) {
        var count = 0
        list.forEach {
            if (it.finished) {
                count++
            }
        }
        val percent = 100 * (count.toFloat() / list.size.toFloat())
        view.updateLovePercent(percent.toDouble())
    }

    fun updatePercentSuccess(list: List<InfoModel>) {
        var count = 0
        list.forEach {
            if (it.finished) {
                count++
            }
        }
        val percent = 100 * (count.toFloat() / list.size.toFloat())
        view.updateSuccessPercent(percent.toDouble())
    }


    interface MyView {
        fun updateLifePercent(percent: Double)
        fun updateSuccessPercent(percent: Double)
        fun updateHappyPercent(percent: Double)
        fun updateLovePercent(percent: Double)
    }
}