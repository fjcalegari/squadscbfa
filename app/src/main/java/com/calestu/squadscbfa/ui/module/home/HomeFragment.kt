package com.calestu.squadscbfa.ui.module.home
import android.os.Bundle
import android.view.View
import com.calestu.squadscbfa.R
import com.calestu.squadscbfa.databinding.FragmentHomeBinding
import com.calestu.squadscbfa.ui.base.fragment.BaseViewModelFragment

class HomeFragment : BaseViewModelFragment<FragmentHomeBinding, HomeViewModel>() {

//    private val quotesPagerAdapter = QuotesPagerAdapter()
//    private lateinit var viewPager: ViewPager2
//
//    private var firstSubmitListChanged = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewmodel = viewModel
        }

//        configureViewPager()
//
//        viewModel.quotesLiveData.watch(this.viewLifecycleOwner) {
//            Timber.d("quotesLiveData")
//            quotesPagerAdapter.submitList(it)
//            if (!firstSubmitListChanged && !it.isNullOrEmpty()) {
//                Timber.d("firstSubmitListChanged")
//                onPageChangeCallback.onPageSelected(0)
//                firstSubmitListChanged = true
//            }
//        }
//
//        viewModel.workInfoLiveData.watch(viewLifecycleOwner) {
//            viewModel.onWorkInfoStateChanged(it)
//        }
//
//        viewModel.viewStateLiveData.watch(this.viewLifecycleOwner) {
//            Timber.d("viewStateLiveData: $it")
//        }
//
//        viewModel.currentItemLiveData.watch(this.viewLifecycleOwner) {
//            configureToogleFavorite(it)
//        }

    }

//    private fun configureToogleFavorite(quoteModelView: QuoteModelView) {
//        Timber.d("configureToogleFavorite: $quoteModelView")
//
//        with(binding.layoutBottomBar.toogleFavorite) {
//
//            if (quoteModelView.favorite) {
//                progress = 1f
//                repeatCount = 1
//                repeatMode = LottieDrawable.REVERSE
//            } else {
//                progress = 0f
//                repeatCount = 0
//            }
//
//            setOnClickListener {
//                playAnimation()
//                viewModel.onToggleFavorite(quoteModelView)
//            }
//        }
//
//    }

//    layoutBottomBar.toogleFavorite.setOnClickListener { v ->
////                layoutBottomBar.toogleFavorite.cancelAnimation()
//        if (tootle == 0) {
//            layoutBottomBar.toogleFavorite.playAnimation()
//        } else if (tootle == 1) {
//            layoutBottomBar.toogleFavorite.repeatCount = 1
//            layoutBottomBar.toogleFavorite.repeatMode = LottieDrawable.REVERSE
//        } else if (tootle == 2) {
//            layoutBottomBar.toogleFavorite.progress = 0f
//            layoutBottomBar.toogleFavorite.playAnimation()
//        } else if (tootle == 3) {
//            layoutBottomBar.toogleFavorite.progress = 1f
//        }
//        tootle = tootle.plus(1)
//        Timber.d("tootle: $tootle")
//        Timber.d("toogleFavorite: ${viewpager.currentItem}")
//    }

//    private fun configureViewPager() {
//        viewPager = binding.viewpager
//        with(viewPager) {
//            binding.viewpager
//            adapter = quotesPagerAdapter
//            registerOnPageChangeCallback(onPageChangeCallback)
////            setPageTransformer(transformer)
//        }
//    }
//
//    private fun handleViewState(homeViewState: HomeViewState) {
////        when(homeViewState.viewState) {
////            HomeViewState.ViewState.SUCCESS -> quotesPagerAdapter.refreshItems(homeViewState.quotesList)
////        }
//    }
//
//    private val transformer = ViewPager2.PageTransformer { page, position ->
//        Timber.d("transformer: $position")
//        Timber.d("transformer: ${viewPager.currentItem}")
//        page.apply {
//            page.alpha = 0.25f + (1 - Math.abs(position))
//        }
//    }
//
//    private val onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
//        override fun onPageSelected(position: Int) {
//            Timber.d("onPageSelected: $position")
//            quotesPagerAdapter.getItemModel(position)?.let {
//                viewModel.onCurrentItemChanged(it)
//                configureToogleFavorite(it)
//            }
//        }
//    }

    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java
    override fun getLayout(): Int = R.layout.fragment_home

}