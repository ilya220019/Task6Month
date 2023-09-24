package vef.ter.hw6_1

import android.view.MenuItem
import androidx.navigation.findNavController
import vef.ter.hw6_1.base.BaseActivity
import vef.ter.hw6_1.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflaterViewBinding() = ActivityMainBinding.inflate(layoutInflater)


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> findNavController(R.id.nav_host_fragment).navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }
}
