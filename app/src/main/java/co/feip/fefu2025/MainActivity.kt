package co.feip.fefu2025

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import co.feip.fefu2025.ui.theme.FEFU2025AndroidBaseRepoTheme

class MainActivity : ComponentActivity() {
    private var counter: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null)
            counter = savedInstanceState.getInt("counter_value", 0)

        val tapable_text: TextView = findViewById(R.id.title)
        val counter_text: TextView = findViewById(R.id.counter)
        counter_text.text = counter.toString()

        tapable_text.setOnClickListener{
            counter++
            counter_text.text = counter.toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("counter_value", counter)
    }
}
