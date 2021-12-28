package com.stephenwoerner.jsonviewdictation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.stephenwoerner.jsonviewdictation.databinding.PageFragmentBinding
import java.lang.Exception

class PageFragment private constructor(private val pageData: Page): Fragment() {

    private lateinit var binding : PageFragmentBinding

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

            text?.let { str ->
                binding.pageTextView.apply {
                    Util.show(this, true)
                    text = str
                }
            }

            image?.let {
                Util.show(binding.pageImageView, true)
                when {
                    image.contains("http://") || image.contains("https://")  -> Picasso.get().load(it).into(binding.pageImageView)
                    image.contains("R.drawable") -> {

                        val localResId = R.drawable.ic_launcher_foreground
                            try {
                            requireContext().resources.getIdentifier(it,"drawable",requireContext().packageName)
                        } catch (e: Exception) {
                            e.printStackTrace()
                            R.drawable.ic_launcher_foreground
                        }
                        binding.pageImageView.setImageDrawable(AppCompatResources.getDrawable(requireContext(), localResId))
                    }
                }
            }
        }
    }

    companion object {
        val TAG = PageFragment::class.java.simpleName ?: "PageFragment"

        fun create(page: Page) : PageFragment{
            return PageFragment(page)
        }
    }
}
