package alirezat775.carouselview

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
        val carousel =
            SliderComponent(this, carousel_view, adapter)
        carousel.setOrientation(SliderRecyclerView.HORIZONTAL, false)
        carousel.autoScroll(false, 5000, true)
        carousel.scaleView(true)
        carousel.lazyLoad(true, object :
            SliderLazyLoadListener {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: SliderRecyclerView) {
                if (hasNextPage) {
                    Log.d(TAG, "load new item on lazy mode")
//                    carousel.add(SampleModel(R.drawable.border_selection))
             /*       carousel.add(SampleModel(R.drawable.ic_asia))
                    carousel.add(SampleModel(R.drawable.ic_australia))
                    carousel.add(SampleModel(R.drawable.ic_europe))
                    carousel.add(SampleModel(R.drawable.ic_south_america))
                    carousel.add(SampleModel(R.drawable.ic_north_america))*/
                    hasNextPage = false
                }
            }
        })
        adapter.setOnClickListener(object : SampleAdapter.OnClick {
            override fun click(model: SampleModel) {
//                carousel.remove(model)
            }
        })
//        carousel.scrollSpeed(100f)
//        carousel.enableSlider(true)

        carousel.addSliderListener(object :
            SliderListener {
            override fun onPositionChange(position: Int) {
                Log.d(TAG, "currentPosition : $position")
            }

            override fun onScroll(dx: Int, dy: Int) {
                Log.d(TAG, "onScroll dx : $dx -- dy : $dx")
            }
        })

//        carousel.add(EmptySampleModel("empty list"))
//        carousel.add(SampleModel(R.drawable.border_selection))
        carousel.add(SampleModel(R.drawable.ic_africa))
        carousel.add(SampleModel(R.drawable.ic_africa))
        carousel.add(SampleModel(R.drawable.ic_africa))
        carousel.add(SampleModel(R.drawable.ic_africa))
        carousel.add(SampleModel(R.drawable.ic_africa))
/*
        carousel.add(SampleModel(R.drawable.ic_asia))
        carousel.add(SampleModel(R.drawable.ic_australia))
        carousel.add(SampleModel(R.drawable.ic_europe))
        carousel.add(SampleModel(R.drawable.ic_south_america))
        carousel.add(SampleModel(R.drawable.ic_north_america))*/


    }
}
