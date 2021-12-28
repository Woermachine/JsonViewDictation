package com.stephenwoerner.jsonviewdictation.demo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.stephenwoerner.jsonviewdictation.AnnouncementsFragment
import com.stephenwoerner.jsonviewdictation.AnnouncementsListener
import com.stephenwoerner.jsonviewdictation.databinding.ActivityFullscreenBinding

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class DemoActivity : AppCompatActivity() {

    companion object {
        val TAG = DemoActivity::class.java.simpleName ?: "DemoActivity"
    }

    private lateinit var binding: ActivityFullscreenBinding

    private lateinit var announcementsFrag: AnnouncementsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFullscreenBinding.inflate(layoutInflater)
    }

    override fun onStart() {
        super.onStart()

        announcementsFrag = AnnouncementsFragment.create("{\n" +
                "  \"progress\" : {\n" +
                "    \"show_progress\" : true,\n" +
                "    \"progress_type\" : \"BAR\",\n" +
                "    \"primary_color\": \"#FF039BE5\",\n" +
                "    \"secondary_color\" : \"#FFBB86FC\"\n" +
                "  },\n" +
                "  \"show_buttons\" : true,\n" +
                "  \"page_array\" : [\n" +
                "    {\n" +
                "      \"text\": \"page 1\",\n" +
                "      \"text_color\": \"#FFFFFFFF\",\n" +
                "      \"background_color\": \"#FF000000\"\n," +
                "      \"image\": \"https://cms-assets.tutsplus.com/cdn-cgi/image/width=1700/uploads/users/523/posts/32694/final_image/tutorial-preview-large.png\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"page 2\",\n" +
                "      \"text_color\": \"#FF000000\",\n" +
                "      \"background_color\": \"#FFFFFFFF\",\n" +
                "      \"image\": \"R.drawable.circle_filled\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"page 3\",\n" +
                "      \"text_color\": \"#FFFFFFFF\",\n" +
                "      \"background_color\": \"#FF000000\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"button_color\" : \"#FF039BE5\",\n" +
                "  \"button_tint\" : \"#FF3700B3\"\n" +
                "}", object: AnnouncementsListener {
            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }
        })


        binding.apply {
            setContentView(root)
            supportFragmentManager
                .beginTransaction()
                .add(contentMain.id, announcementsFrag)
                .commit()
        }


    }

}
