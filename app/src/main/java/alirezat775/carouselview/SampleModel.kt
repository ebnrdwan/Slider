package alirezat775.carouselview

import ebnrdwan.lib.slider.ISliderModel

class SampleModel constructor(private var id: Int) : ISliderModel {

    fun imageId(): Int {
        return id
    }
}
