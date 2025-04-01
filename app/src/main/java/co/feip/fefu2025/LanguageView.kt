package co.feip.fefu2025
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class LanguageItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val colorCircle: View
    private val tvLanguageName: TextView
    private val tvLanguagePercent: TextView

    init {
        inflate(context, R.layout.view_language_stat, this)

        colorCircle = findViewById(R.id.colorCircle)
        tvLanguageName = findViewById(R.id.tvLanguageName)
        tvLanguagePercent = findViewById(R.id.tvLanguagePercent)

        orientation = HORIZONTAL
    }

    fun setLanguageName(name: String) {
        tvLanguageName.text = name
    }

    fun setCircleColor(hexColor: Long) {
        val colorInt = hexColor.toInt()
        colorCircle.background.setTint(colorInt)
    }

    fun setUsagePercent(percent: Float) {
        tvLanguagePercent.text = "%.1f%%".format(percent)
    }
}
