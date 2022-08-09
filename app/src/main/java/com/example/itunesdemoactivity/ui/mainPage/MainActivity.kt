package com.example.itunesdemoactivity.ui.mainPage

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itunesdemoactivity.R
import com.example.itunesdemoactivity.databinding.ActivityMainBinding
import com.example.itunesdemoactivity.ui.mainPage.ui.ItunesAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import android.media.AudioManager


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ItunesAdapter.IClickListener {
    private lateinit var mBinding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var mAdapter: ItunesAdapter
    private lateinit var mediaPlayer: MediaPlayer
    private var lastSong = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initObserver()
        initListener()
        mBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            mAdapter = ItunesAdapter(this@MainActivity)
            adapter = mAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        mediaPlayer = MediaPlayer()
    }

    private fun initListener() {
        mBinding.searchField.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->

            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // Do something of your interest.
                // We in this examples created the following Toasts
                viewModel.filterWord.set(mBinding.searchField.text.toString())
                return@OnEditorActionListener true
            }
            false
        })

    }

    private fun initObserver() {
        viewModel.res.observe(this, {
            mAdapter.infoList = it.filter { info ->
                info.artistName.isNotEmpty() || info.trackName.isNotEmpty()
                        || info.previewUrl.isNotEmpty()
            }
        })
        viewModel.filterWord.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                val startSearchingText = viewModel.filterWord.get()!!.replace(" ", "+")
                viewModel.getItunesInfo(startSearchingText)
            }
        })
    }

    override fun onClick(url: String) {
        playAudio(url)
    }

    private fun playAudio(url: String) {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.reset()
        } else {
            mediaPlayer = MediaPlayer()
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
            try {
                mediaPlayer.setDataSource(url)
                mediaPlayer.prepare()
                mediaPlayer.start()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            Toast.makeText(this, "Audio started playing..", Toast.LENGTH_SHORT).show()

        }
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer.reset()
        mediaPlayer.release();
    }
}