package com.kjbriyan.socialapps.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.kjbriyan.socialapps.R
import com.kjbriyan.socialapps.ui.fragment.acount.AcountFragment

import com.kjbriyan.socialapps.ui.fragment.FeedFragment
import com.kjbriyan.socialapps.ui.fragment.dashboard.DashboardFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        setFragment(DashboardFragment.newIntance())
        navigation.add(MeowBottomNavigation.Model(1,R.drawable.home))
        navigation.add(MeowBottomNavigation.Model(2,R.drawable.rating))
        navigation.add(MeowBottomNavigation.Model(3,R.drawable.user))

        navigation.setOnClickMenuListener{
            when(it.id){
                1 -> setFragment(DashboardFragment.newIntance())
                2 -> setFragment(FeedFragment.newIntance())
                3 -> setFragment(AcountFragment.newIntance())


                else -> " "
            }
        }
        navigation.show(1)
    }

    fun setFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.framelayout, fragment, "HomeActivity")
            .commit()
    }
}