package com.teach.teach1907.utils

import androidx.viewpager.widget.ViewPager

/**
 * Created by 任小龙 on 2020/3/22.
 */
abstract class PagerSelectedListener : ViewPager.OnPageChangeListener {
    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        pageSelected(position)
    }

    abstract fun pageSelected(position: Int)
}