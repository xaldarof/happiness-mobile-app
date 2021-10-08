package pdf.reader.happiness.tools

import pdf.reader.happiness.core.InfoModel

interface PercentCalculator {

    fun calculatePercent(list: List<InfoModel>): Float
    fun updateAllFinished(list: List<InfoModel>):Boolean
    fun calculateIsAllFinished(list: List<InfoModel>): Boolean


    class Base : PercentCalculator {
        override fun calculatePercent(list: List<InfoModel>): Float {
            var count = 0
            list.forEach {
                if (it.finished) {
                    count++
                }
            }

            return 100 * (count.toFloat() / list.size.toFloat())
        }

        override fun updateAllFinished(list: List<InfoModel>):Boolean{
            var count = 0
            list.forEach {
                if (!it.finished){
                    count++
                }
            }
           return count==0
        }

        override fun calculateIsAllFinished(list: List<InfoModel>): Boolean {
            var finishedCounter = 0
            val total = list.size
            list.forEach {
                if (it.finished) {
                    finishedCounter++
                }
            }
            return finishedCounter == total
        }

    }

}