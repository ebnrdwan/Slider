package alirezat775.lib.carouselview



interface CarouselLazyLoadListener {

    fun onLoadMore(page: Int, totalItemsCount: Int, view: CarouselView)

}
