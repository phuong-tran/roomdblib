package com.pt.room.lib.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.coroutineScope
import com.pt.room.lib.base.FragmentSupportViewBinding
import com.pt.room.lib.databinding.FragmentDashboardBinding
import com.pt.room.lib.db.StudentDaoProxy
import com.pt.room.lib.db.StudentEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : FragmentSupportViewBinding<FragmentDashboardBinding>() {

    @Inject
    lateinit var studentDaoProxy: StudentDaoProxy

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): FragmentDashboardBinding = FragmentDashboardBinding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        insertButton.setOnClickListener {
            lifecycle.coroutineScope.launch {
                val counter = next
                studentDaoProxy.insert(StudentEntity(name = "Phuong Tran $counter", age = counter))
            }
        }
        deleteAll.setOnClickListener {
            lifecycle.coroutineScope.launch {
                studentDaoProxy.deleteAll().also {
                    counter.set(0)
                }
            }
        }
    }

    companion object {
        private val counter = AtomicInteger(0)
        val next get() = counter.incrementAndGet()
    }
}