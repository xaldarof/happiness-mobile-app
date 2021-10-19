package pdf.reader.happiness.data.cloud.data_insert

class TokenIdGenerator {
    fun getGeneratedId():String{
        val base = "ab12cd2323efgh312ij13klmnopqrst3uvwx3yzAB3C33212DEFG4H2IJ4K54MN4OPQ1242TUV21354125WXYZ12eawe3425awewa617890"
        var id = ""
        for (i in 0 until 13) {
            id+=base[(base.indices).random()]
        }
        return id
    }
}