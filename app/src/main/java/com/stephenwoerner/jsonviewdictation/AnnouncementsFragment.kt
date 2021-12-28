package com.stephenwoerner.jsonviewdictation

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.stephenwoerner.jsonviewdictation.databinding.AnnouncementsFragmentBinding

/**
 * Fragment holding all UI elements for the announcement flow. Place in desired root fragment holder
 */
class AnnouncementsFragment private constructor(private val pageParams: JsonViewPages, private val listener : AnnouncementsListener ) : Fragment() {

    private val onClickListener = View.OnClickListener {
        when(it.id) {
            binding.nextButton.id -> {
                nextPage()
            }
            binding.prevButton.id -> {
                prevPage()
            }
            binding.containerView.id -> {
                nextPage()
            }
        }
    }

    private lateinit var binding: AnnouncementsFragmentBinding
    private lateinit var pageFragments : Array<Fragment>

    private var pageIndex = -1


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AnnouncementsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            nextButton.setOnClickListener(onClickListener)
            prevButton.setOnClickListener(onClickListener)
        }


        pageParams.apply {

            Util.show(binding.nextButton, showButtons)
            Util.show(binding.prevButton, showButtons)
            setFabColor(buttonColor)
            setFabTint(buttonTint)

            pageArray?.apply {
                pageFragments = Array(size) { i ->
                    PageFragment.create(this[i])
                }

                forEachIndexed { i, _ ->
                    val dotImageView = View.inflate(requireContext(), R.layout.dot_imageview, null) as ImageView
                    dotImageView.tag = "dot$i"

                    val secondaryColor = Color.parseColor(pageParams.progress?.secondaryColor ?: "#FF00FF00")

                    dotImageView.imageTintList = ColorStateList.valueOf(secondaryColor)
                    dotImageView.setImageResource(R.drawable.circle_outline)
                    binding.dotContainer.addView(dotImageView)

                }

                binding.progressBar.max = size
            }

            progress?.apply {
                val viewToShow = when (progressType) {
                    ProgressType.BAR -> binding.progressBar
                    ProgressType.DOTS -> binding.dotContainer
                    else -> null
                }

                Util.show(viewToShow, showProgress)

            }
        }

        childFragmentManager
            .beginTransaction()
            .add(binding.containerView.id, pageFragments[0])
            .commit()

        setProgress(0)
    }

    private fun nextPage() {
        if(pageIndex >= pageFragments.lastIndex) {
            listener.onComplete()
            return
        }

        setProgress(pageIndex + 1)
        childFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
            )
            .replace(binding.containerView.id, pageFragments[pageIndex])
            .commit()
    }

    private fun prevPage() {
        if(pageIndex == 0)
            return

        setProgress(pageIndex - 1)

        childFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_left,
                R.anim.slide_out_right,
            )
            .replace(binding.containerView.id, pageFragments[pageIndex])
            .commit()
    }

    private fun setProgress(newPageIndex: Int) {
        val primaryColor = Color.parseColor(pageParams.progress?.primaryColor)
        val secondaryColor = Color.parseColor(pageParams.progress?.secondaryColor)

        if(pageIndex != -1) {
            val oldDotView = binding.dotContainer.findViewWithTag<ImageView>("dot$pageIndex")
            oldDotView?.apply {
                setImageResource(R.drawable.circle_outline)
                isSelected = false
                imageTintList = ColorStateList.valueOf(secondaryColor)
            }
        }

        pageIndex = newPageIndex

        val newDotView = binding.dotContainer.findViewWithTag<ImageView>("dot$pageIndex")
        newDotView?.let {
            newDotView.setImageResource(R.drawable.circle_filled)
            newDotView.isSelected = true
            newDotView.imageTintList = ColorStateList.valueOf(primaryColor)
        }

        binding.progressBar.progress = newPageIndex + 1
    }

    private fun setFabTint(colorRef: String?) {
        colorRef ?: return

        val tint = Color.parseColor(colorRef)
        binding.nextButton.imageTintList = ColorStateList.valueOf(tint)
        binding.prevButton.imageTintList = ColorStateList.valueOf(tint)
    }

    private fun setFabColor(colorRef: String?) {
        colorRef ?: return

        val tint = Color.parseColor(colorRef)
        binding.nextButton.backgroundTintList = ColorStateList.valueOf(tint)
        binding.prevButton.backgroundTintList = ColorStateList.valueOf(tint)
    }

    companion object {
        val TAG = AnnouncementsFragment::class.java.simpleName ?: "AnnouncementsFragment"

        /**
         * Create an AnnouncementsFragments based the given JSON string
         * @param jsonStr a string of JSON to parse
         * @param newListener an interface for AnnouncementsListener to communicate with host application
         */
        fun create(jsonStr : String, newListener: AnnouncementsListener) : AnnouncementsFragment {
            return create(Gson().fromJson(jsonStr, JsonViewPages::class.java), newListener)
        }

        /**
         * Create an AnnouncementsFragments based the given JSON string
         * @param newParams a JsonViewPages serving as the basis of the displayed announcements
         * @param newListener an interface for AnnouncementsListener to communicate with host application
         */
        fun create(newParams : JsonViewPages, newListener: AnnouncementsListener) : AnnouncementsFragment {
            return AnnouncementsFragment(newParams, newListener)
        }
    }
}
