package com.stephenwoerner.jsonviewdictation.demo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
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
                "    \"progress_type\" : \"DOTS\",\n" +
                "    \"primary_color\": \"#FF039BE5\",\n" +
                "    \"secondary_color\" : \"#FFBB86FC\"\n" +
                "  },\n" +
                "  \"show_buttons\" : true,\n" +
                "  \"page_array\" : [\n" +
                "    {\n" +
                "      \"text\": \"page 1\",\n" +
                "      \"text_color\": \"#FFFFFFFF\",\n" +
                "      \"background_color\": \"#FF000000\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"text\": \"page 2\",\n" +
                "      \"text_color\": \"#FF000000\",\n" +
                "      \"background_color\": \"#FFFFFFFF\"\n" +
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