package pdf.reader.happiness.tools

interface WordGenerator {

    fun getRandomWords()

    class Base : WordGenerator {
        override fun getRandomWords() {
            val list = ArrayList<String>()

            list.add("")
            list.add("")
            list.add("")
            list.add("")
            list.add("")
            list.add("")
            list.add("")
            list.add("")
            list.add("")
            list.add("")
            list.add("")
            list.add("")
            list.add("")
        }
    }
}