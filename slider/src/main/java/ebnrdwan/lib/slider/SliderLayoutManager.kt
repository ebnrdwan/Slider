package ebnrdwan.lib.slider

import android.content.Context
import android.graphics.PointF
import android.util.DisplayMetrics
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView


 class SliderLayoutManager(context: Context?, orientation: Int, reverseLayout: Boolean) :
    LinearLayoutManager(context, orientation, reverseLayout) {

    private val shrinkAmount = 0.4f
    private val shrinkDistance = 0.9f
    private var smoothScroller: SmoothScroller? = null

    var anchor: Int = 0

    private var scaleView = true

    init {
        if (smoothScroller == null) {
            smoothScroller = SmoothScroller(context)
        }
    }

    /**
     * @param scaleView set center view scale
     */
    fun scaleView(scaleView: Boolean) {
        this.scaleView = scaleView
    }

    private fun isScaleView(): Boolean {
        return scaleView
    }

    /**
     * @param recycler
     * @param state
     */
    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State) {
        super.onLayoutChildren(recycler, state)
        scrollVerticallyBy(0, recycler, state)
        scrollHorizontallyBy(0, recycler, state)
    }

    /**
     * @param dy
     * @param recycler
     * @param state
     * @return
     */
    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val orientation = orientation
        if (orientation == VERTICAL) {
            val scrolled = super.scrollVerticallyBy(dy, recycler, state)
            if (isScaleView()) {
                val midpoint = height / 2f
                val d0 = 0f
                val d1 = shrinkDistance * midpoint
                val s0 = 1f
                val s1 = 1f - shrinkAmount
                for (i in 0 until childCount) {
                    val child = getChildAt(i)
                    val childMidpoint = (getDecoratedBottom(child!!) + getDecoratedTop(child)) / 2f
                    val d = Math.min(d1, Math.abs(midpoint - childMidpoint))
                    val scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0)
                    child.scaleX = scale
                    child.scaleY = scale

                }
            }
            return scrolled
        } else {
            return 0
        }
    }

    /**
     * @param dx
     * @param recycler
     * @param state
     * @return
     */
    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val orientation = orientation
        if (orientation == HORIZONTAL) {
            val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
            if (isScaleView()) {
                val midpoint = width / 2f
                val d0 = 0f
                val d1 = shrinkDistance * midpoint
                val s0 = 1f
                val s1 = 1f - shrinkAmount
                for (i in 0 until childCount) {
                    val child = getChildAt(i)
                    val childMidpoint = (getDecoratedRight(child!!) + getDecoratedLeft(child)) / 2f
                    val d = Math.min(d1, Math.abs(midpoint - childMidpoint))
                    val scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0)
                    child.scaleX = scale
                    child.scaleY = scale

                    fadeBorder(child, scale, 0.5f)
                    fadeView(child, scale, 0.3f)
                }

            }
            return scrolled
        } else {
            return 0
        }
    }

    private fun fadeBorder(view: View, alpha: Float, subtractade: Float) {
        val border = view.findViewById<View>(R.id.border_view)
        if (alpha in 0.75f..1.0f)
            border?.alpha = alpha
        else border?.alpha = 0.0f

    }

    private fun fadeView(view: View, alpha: Float, subtractade: Float) {

        if (alpha in 0.90f..1.0f)
            view.alpha = alpha
        else view.alpha = alpha - subtractade

    }

    /**
     * @param scrollSpeed milliSecond per inch
     */
    fun setScrollSpeed(scrollSpeed: Float) {
        smoothScroller!!.setScrollSpeed(scrollSpeed)
    }

    /**
     * @param recyclerView
     * @param state
     * @param position
     */
    override fun smoothScrollToPosition(recyclerView: RecyclerView, state: RecyclerView.State?, position: Int) {
        smoothScroller!!.targetPosition = position
        startSmoothScroll(smoothScroller)
    }

    inner class SmoothScroller(context: Context?, private var speed: Float = 150f) : LinearSmoothScroller(context) {

        /**
         * @param scrollSpeed milliSecond per inch
         */
        fun setScrollSpeed(scrollSpeed: Float) {
            speed = scrollSpeed
        }

        /**
         * @param targetPosition
         * @return
         */
        override fun computeScrollVectorForPosition(targetPosition: Int): PointF? {
            return this@SliderLayoutManager.computeScrollVectorForPosition(targetPosition)
        }

        /**
         * @param viewStart
         * @param viewEnd
         * @param boxStart
         * @param boxEnd
         * @param snapPreference
         * @return position view
         */
        override fun calculateDtToFit(
            viewStart: Int,
            viewEnd: Int,
            boxStart: Int,
            boxEnd: Int,
            snapPreference: Int
        ): Int {
            return (boxStart + boxEnd) / 2 - (viewStart + viewEnd) / 2
        }

        /**
         * @param displayMetrics
         * @return
         */
        override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
            return speed / displayMetrics.densityDpi;
        }
    }
}