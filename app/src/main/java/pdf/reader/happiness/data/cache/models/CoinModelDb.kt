package pdf.reader.happiness.data.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey

//USER COIN COUNT

@Entity(tableName = "coins")
data class CoinModelDb(

    @PrimaryKey
    val name: String = "UCC",

    val coinCount: Int)