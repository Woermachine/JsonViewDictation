package com.stephenwoerner.jsonviewdictation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.stephenwoerner.jsonviewdictation.databinding.PageFragmentBinding

class PageFragment private constructor(private val pageData: Page): Fragment() {

    private lateinit var binding : PageFragmentBinding

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val inflater = TransitionInflater.from(requireContext())
////        enterTransition = inflater.inflateTransition(R.transition.slide_in_right)
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PageFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pageData.apply {
            backgroundColor?.let { Util.setColor(binding.pageView, it) }
            Util.setTextColor(binding.pageTextView, textColor)
            binding.pageTextView.text = text

            this.image //TODO
        }
    }



    companion object {
        val TAG = PageFragment::class.java.simpleName ?: "PageFragment"

        fun create(page: Page) : PageFragment{
            return PageFragment(page)
        }
    }
}