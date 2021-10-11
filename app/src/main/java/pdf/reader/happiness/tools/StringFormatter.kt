package pdf.reader.happiness.tools

fun String.formatForDatabase(): String {
    return this.replace(".", " ")
        .replace("#", " ")
        .replace("$", " ")
        .replace("[", " ")
        .replace("]", " ")
        .trim()

}