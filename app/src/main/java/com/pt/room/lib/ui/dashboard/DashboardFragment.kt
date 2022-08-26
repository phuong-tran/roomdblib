package com.pt.room.lib.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.coroutineScope
import com.pt.room.lib.TestActivity
import com.pt.room.lib.base.FragmentSupportViewBinding
import com.pt.room.lib.databinding.FragmentDashboardBinding
import com.pt.room.lib.db.StudentDaoProxy
import com.pt.room.lib.db.StudentEntity
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_dashboard.deleteAll
import kotlinx.android.synthetic.main.fragment_dashboard.insertButton
import kotlinx.android.synthetic.main.fragment_dashboard.startTestActivity
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger

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
            testInsertSingle()
        }
        deleteAll.setOnClickListener {
            testDeleteAllSuspend()
        }
        startTestActivity.setOnClickListener {
            startActivity(Intent(requireActivity(), TestActivity::class.java))
        }
    }

    private fun testInsertSuspend() {
        lifecycle.coroutineScope.launch {
            val counter = next
            studentDaoProxy.insert(StudentEntity(name = "Phuong Tran $counter", age = counter))
                .also {
                    Log.d("PHUONGTRAN", "inserted Record = $it")
                }
        }
    }

    private fun testInsertSingle() {
        val counter = next
        studentDaoProxy.insertSingle(
            StudentEntity(name = "Phuong Tran $counter", age = counter)
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            Log.d("PHUONGTRAN", "olala $it")
        }, {
            Log.d("PHUONGTRAN", "Error $it")
        })
    }

    private fun testDeleteAllSuspend() {
        lifecycle.coroutineScope.launch {
            studentDaoProxy.deleteAll().also {
                counter.set(0)
            }
        }
    }

    companion object {
        private val counter = AtomicInteger(0)
        val next get() = counter.incrementAndGet()
    }
}