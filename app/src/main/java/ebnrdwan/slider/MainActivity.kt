package ebnrdwan.slider

import alirezat775.sliderview.R
import ebnrdwan.lib.slider.SliderComponent
import ebnrdwan.lib.slider.SliderLazyLoadListener
import ebnrdwan.lib.slider.SliderListener
import ebnrdwan.lib.slider.SliderRecyclerView
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var hasNextPage: Boolean = true
    val TAG: String = this::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = SampleAdapter()
        val sliderComponent =
            SliderComponent(this, slider_view, adapter)
        sliderComponent.setOrientation(SliderRecyclerView.HORIZONTAL, false)
        sliderComponent.autoScroll(false, 5000, true)
        sliderComponent.scaleView(true)

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
