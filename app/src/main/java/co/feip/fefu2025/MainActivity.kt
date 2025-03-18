package co.feip.fefu2025
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    private lateinit var myFlexLayout: MyFlexBoxLayout
    private lateinit var btnAdd: Button

    private val languages = listOf(
        "C++",
        "C",
        "Kotlin",
        "Java",
        "Go",
        "Python",
        "C#",
        "JS",
        "PHP",
        "R",
        "SQL",
        "Swift",
        "Perl",
        "ASM",
        "VB",
        "Ruby",
        "ObjC",
        "Rust",
        "D",
        "Dart",
        "COBOL",
        "Julia",
        "Scala",
        "Scheme",
        "Ada",
        "Lisp",
        "Apex",
        "Lua",
        "Fortran",
        "Haskell",
        "TS",
        "Algol",
        "Bash",
        "Carbon",
        "Curl",
        "Elixir",
        "F#",
        "ML",
        "OCaml",
        "OpenCL",
        "Zig",
        "Hello World!"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myFlexLayout = findViewById(R.id.myFlexLayout)
        btnAdd = findViewById(R.id.btnAdd)

        btnAdd.setOnClickListener {
            addRandomLanguageView()
        }
    }

    private fun addRandomLanguageView() {
        val langName = languages.random()

        val circleColor = getRandomColor()

        val randomPercent = Random.nextDouble(0.0, 100.0).toFloat()

        val itemView = LanguageItemView(this).apply {
            setLanguageName(langName)
            setCircleColor(circleColor)
            setUsagePercent(randomPercent)
        }

        myFlexLayout.addView(itemView)
    }

    private fun getRandomColor(): Int {
        val red = (0..255).random()
        val green = (0..255).random()
        val blue = (0..255).random()
        return Color.rgb(red, green, blue)
    }
}

