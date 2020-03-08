package ebnrdwan.slider

import alirezat775.sliderview.R
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ebnrdwan.lib.slider.SliderComponent
import ebnrdwan.lib.slider.SliderListener
import ebnrdwan.lib.slider.SliderRecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG: String = this::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = SampleAdapter()
        val msliderManager = MSliderLayoutManager(this, SliderRecyclerView.HORIZONTAL, false)
        val sliderComponent =
            SliderComponent(msliderManager, slider_view, adapter)
        sliderComponent.autoScroll(false, 5000, true)
        sliderComponent.setCalculateCenterThreshold(true)

        adapter.setOnClickListener(object :
            SampleAdapter.OnClick {
            override fun click(model: SampleModel) {
//                slider.remove(model)
            }
        })

        sliderComponent.addSliderListener(object :
            SliderListener {
            override fun onPositionChange(position: Int) {
                Log.d(TAG, "currentPosition : $position")
            }

            override fun onScroll(dx: Int, dy: Int) {
                Log.d(TAG, "onScroll dx : $dx -- dy : $dx")
            }
        })

        sliderComponent.add(SampleModel(R.drawable.ic_africa))
        sliderComponent.add(SampleModel(R.drawable.ic_africa))
        sliderComponent.add(SampleModel(R.drawable.ic_africa))
        sliderComponent.add(SampleModel(R.drawable.ic_africa))
        sliderComponent.add(SampleModel(R.drawable.ic_africa))



    }
}
