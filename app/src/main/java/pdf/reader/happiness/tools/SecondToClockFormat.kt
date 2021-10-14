package pdf.reader.happiness.tools

fun Int.toClock(): String {
    val hours = this / 3600
    val minutes = (this % 3600) / 60
    val seconds = this % 60

    return "$hours:$minutes:$seconds"
}