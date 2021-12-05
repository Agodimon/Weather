package com.bignerdranch.android.androidwithkotlin.framework.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bignerdranch.android.androidwithkotlin.R
import com.bignerdranch.android.androidwithkotlin.databinding.MainActivityBinding
import com.bignerdranch.android.androidwithkotlin.framework.ui.contacts.ContactsFragment
import com.bignerdranch.android.androidwithkotlin.framework.ui.history.HistoryFragment
import com.bignerdranch.android.androidwithkotlin.framework.ui.main.MainFragment

class MainActivity : AppCompatActivity(R.layout.main_activity) {
    private val binding by viewBinding(MainActivityBinding::bind)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.menu_history -> {
                openFragment(HistoryFragment.newInstance())
                true
            }
            R.id.menu_contacts -> {
                openFragment(ContactsFragment.newInstance())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.apply {
            beginTransaction()
                .add(R.id.container, fragment)
                .addToBackStack("")
                .commitAllowingStateLoss()
        }
    }
}