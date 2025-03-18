package co.feip.fefu2025
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import kotlin.math.max

class MyFlexBoxLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val maxWidth = MeasureSpec.getSize(widthMeasureSpec)

        var currentLineWidth = 0
        var currentLineHeight = 0
        var totalHeight = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (child.visibility == GONE) {
                continue
            }

            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            val childWidth = child.measuredWidth
            val childHeight = child.measuredHeight

            if (currentLineWidth + childWidth > maxWidth) {
                totalHeight += currentLineHeight
                currentLineWidth = childWidth
                currentLineHeight = childHeight
            } else {
                currentLineWidth += childWidth
                currentLineHeight = max(currentLineHeight, childHeight)
            }
        }

        totalHeight += currentLineHeight

        val finalWidth = if (widthMode == MeasureSpec.EXACTLY) maxWidth else currentLineWidth
        val finalHeight = if (heightMode == MeasureSpec.EXACTLY) {
            MeasureSpec.getSize(heightMeasureSpec)
        } else {
            totalHeight
        }

        setMeasuredDimension(finalWidth, finalHeight)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        val maxWidth = right - left

        var xPos = 0
        var yPos = 0
        var lineHeight = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (child.visibility == GONE) {
                continue
            }
            val childWidth = child.measuredWidth
            val childHeight = child.measuredHeight

            if (xPos + childWidth > maxWidth) {
                xPos = 0
                yPos += lineHeight
                lineHeight = 0
            }

            child.layout(
                xPos,
                yPos,
                xPos + childWidth,
                yPos + childHeight
            )

            xPos += childWidth
            lineHeight = max(lineHeight, childHeight)
        }
    }
}

