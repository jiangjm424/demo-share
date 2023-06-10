package com.grank.db.demo

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.grank.db.demo.databinding.ActivityMainBinding
import com.happy.ishare.ShareCore
import com.happy.ishare.ShareSDK
import com.happy.ishare.core.Scene
import com.happy.ishare.core.ShareEntity
import com.happy.ishare.core.ShareParam
import com.happy.ishare.link.CopyLink
import com.happy.ishare.link.LinkShareParam
import com.happy.ishare.ui.ShareDialog
import com.happy.ishare.utils.toByteArray
import com.happy.ishare.utils.toThumbnail

class MainActivity : AppCompatActivity(), ShareDialog.ShareClickListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getSharedPreferences("db_viewer", MODE_PRIVATE).edit().putBoolean("pub.db", false)
            .putString("sss", "string").commit()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener {
            ShareDialog().show(supportFragmentManager, "")
        }
    }

    override fun onClick(scene: Scene, pos: Int) {
        Log.i("jiang", "scene:$scene, platform:${scene.platform}, pos:$pos")
        ShareCore.Builder(scene.platform)
            .shareEntity(
                ShareEntity().apply {
                    title = "title"
                    description = "desc"
                    url = "www.baidu.com"
                    this.scene = scene
                    thumbData = BitmapFactory.decodeResource(resources, R.drawable.wechat)
                        .toThumbnail(150, 150).toByteArray()
                }
            ).scene(scene)
            .build()?.share()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                ShareSDK.allSupportShareScene().forEach {
                    Log.i("jiang", "scene:$it")
                }
                ShareCore.Builder(CopyLink)
                    .shareEntity(
                        ShareEntity().apply {
                            title = "title"
                            description = "desc"
                            url = "www.baidu.com"
                            thumbData = BitmapFactory.decodeResource(resources, R.drawable.wechat)
                                .toByteArray()
                        }
                    ).build()?.share(object : ShareCore.ShareCallback {
                        override fun onSuccess() {
                            Log.i("jiang", "copy sucess")
                        }

                        override fun onFail(errCode: Int, errMsg: String?) {
                            Log.i("jiang", "copy fail:$errMsg")
                        }
                    })
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
            || super.onSupportNavigateUp()
    }

}
