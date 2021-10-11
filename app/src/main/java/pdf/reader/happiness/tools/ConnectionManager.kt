package pdf.reader.happiness.tools

import android.content.Context
import android.net.NetworkInfo
import android.net.ConnectivityManager
import android.net.wifi.WifiManager


interface Connection{
    fun enableWifi()
    fun isConnected(): Boolean
}

class ConnectionManager(private val context: Context) : Connection {

    override fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return connectivityManager!!.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)!!.state == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)!!.state == NetworkInfo.State.CONNECTED
    }
    override fun enableWifi(){
        val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        wifiManager.isWifiEnabled = true
    }
}