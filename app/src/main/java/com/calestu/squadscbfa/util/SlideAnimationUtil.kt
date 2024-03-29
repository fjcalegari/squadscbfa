//package com.calestu.squadscbfa.util
//
//import android.content.Context
//import android.view.View
//import android.view.animation.AnimationUtils
//import com.calestu.squadscbfa.R
//
//class SlideAnimationUtil {
//
//    /**
//     * Animates a view so that it slides in from the left of it's container.
//     *
//     * @param context
//     * @param view
//     */
//    fun slideInFromLeft(context: Context, view: View) {
//        runSimpleAnimation(context, view, R.anim.slide_from_left);
//    }
//
//    /**
//     * Animates a view so that it slides from its current position, out of view to the left.
//     *
//     * @param context
//     * @param view
//     */
//    fun slideOutToLeft(context: Context, view: View) {
//        runSimpleAnimation(context, view, R.anim.slide_to_left);
//    }
//
//    /**
//     * Animates a view so that it slides in the from the right of it's container.
//     *
//     * @param context
//     * @param view
//     */
//    fun slideInFromRight(context: Context, view: View) {
//        runSimpleAnimation(context, view, R.anim.slide_from_right);
//    }
//
//    /**
//     * Animates a view so that it slides from its current position, out of view to the right.
//     *
//     * @param context
//     * @param view
//     */
//    fun slideOutToRight(context: Context, view: View) {
//        runSimpleAnimation(context, view, R.anim.slide_to_right);
//    }
//
//    /**
//     * Runs a simple animation on a View with no extra parameters.
//     *
//     * @param context
//     * @param view
//     * @param animationId
//     */
//    private fun runSimpleAnimation(context: Context, view: View, animationId: Int) {
//        view.startAnimation(AnimationUtils.loadAnimation(
//            context, animationId
//        ))
//    }
//
//}