package pdf.reader.happiness.core

import java.io.Serializable

data class MissionModel(val name: String, val progress: Float, val fragmentName: FragmentName) :
    Serializable