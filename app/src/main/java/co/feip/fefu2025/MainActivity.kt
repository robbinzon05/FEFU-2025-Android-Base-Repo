package co.feip.fefu2025

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat

class InternetConnection : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val connectivity_manager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivity_manager.activeNetwork
        val capabilities = connectivity_manager.getNetworkCapabilities(network)
        val is_connected = capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
        val message = if (is_connected)
            "Internet is enabled"
        else
            "Internet is disabled"

        Log.d("InternetConnection", message)
    }
}

class MainActivity : ComponentActivity() {
    private var counter: Int = 0
    private val internet_reciever = InternetConnection()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        @Suppress("DEPRECATION")
        ContextCompat.registerReceiver(
            this,
            internet_reciever,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION),
            ContextCompat.RECEIVER_EXPORTED
        )

        setContentView(R.layout.activity_main)
        if (savedInstanceState != null)
            counter = savedInstanceState.getInt("counter_value", 0)

        val tapable_text: TextView = findViewById(R.id.title)
        val counter_text: TextView = findViewById(R.id.counter)
        counter_text.text = counter.toString()

        tapable_text.setOnClickListener {
            counter++

            counter_text.text = counter.toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("counter_value", counter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(internet_reciever)
    }
}
