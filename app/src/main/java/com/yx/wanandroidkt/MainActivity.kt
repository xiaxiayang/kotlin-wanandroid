package com.yx.wanandroidkt

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.yx.wanandroidkt.base.BaseActivity
import com.yx.wanandroidkt.vm.HomeVM
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val model: HomeVM by viewModels<HomeVM>()
        model.getDatas().observe(this, Observer<Test>{ users ->
            // update UI
            Toast.makeText(this,users.toString(),Toast.LENGTH_LONG).show()
        })

    }
}

