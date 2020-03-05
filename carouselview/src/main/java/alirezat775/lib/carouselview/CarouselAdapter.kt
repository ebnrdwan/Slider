package alirezat775.lib.carouselview

import alirezat775.lib.carouselview.helper.ViewHelper
import alirezat775.lib.carouselview.helper.fade
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Build
import android.view.View
import android.view.animation.BaseInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import androidx.recyclerview.widget.RecyclerView
import java.util.*


abstract class CarouselAdapter : RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    companion object {
        const val REMOVE = 1
        const val ADD = 2
    }

    private lateinit var recyclerView: RecyclerView
    private var enableSlider = false
    private var items: MutableList<CarouselModel> = ArrayList()

    private fun imageOption(view: View) {
        view.layoutParams.width = Math.round(ViewHelper.getScreenWidth().toDouble()).toInt()
        view.layoutParams.height = Math.round(view.layoutParams.width * 0.60).toInt()
        view.requestLayout()
    }


    private fun fadeOption(view: View) {
        view.layoutParams.width = Math.round(ViewHelper.getScreenWidth().toDouble()).toInt()
        view.layoutParams.height = Math.round(view.layoutParams.width * 0.60).toInt()
        view.requestLayout()
    }
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    /**
     * @return list items
     */
    fun getItems(): MutableList<CarouselModel> {
        return items
    }

    fun operation(item: CarouselModel, operation: Int) {
        when (operation) {
            ADD -> add(item)
            REMOVE -> remove(item)
        }
    }

    fun addAll(items: MutableList<CarouselModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    private fun add(item: CarouselModel) {
        recyclerView.post {
            notifyItemInserted(itemCount - 1)
            getItems().add(item)
        }
    }

    private fun remove(item: CarouselModel) {
        notifyItemRemoved(getItems().indexOf(item))
        getItems().remove(item)
    }

    private fun isEnableSlider(): Boolean {
        return enableSlider
    }

    fun enableSlider(enableSlider: Boolean) {
        this.enableSlider = enableSlider
    }

    open inner class CarouselViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            if (isEnableSlider()) imageOption(itemView)
        }
    }
}