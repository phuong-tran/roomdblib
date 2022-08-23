package com.pt.room.lib

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pt.room.lib.databinding.TestActivityLayoutBinding
import com.pt.room.lib.fragments.Fragment1
import com.pt.room.lib.fragments.Fragment2

class TestActivity: AppCompatActivity() {

    private lateinit var binding: TestActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TestActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.show1Hide2.setOnClickListener {
            binding.fragmentContainerView1.visibility = View.VISIBLE
            binding.fragmentContainerView2.visibility = View.GONE
        }

        binding.show2Hide1.setOnClickListener {
            binding.fragmentContainerView1.visibility = View.GONE
            binding.fragmentContainerView2.visibility = View.VISIBLE
        }
    }
}