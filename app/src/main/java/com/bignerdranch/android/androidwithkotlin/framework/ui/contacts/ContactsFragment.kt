package com.bignerdranch.android.androidwithkotlin.framework.ui.contacts

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bignerdranch.android.androidwithkotlin.R
import com.bignerdranch.android.androidwithkotlin.databinding.FragmentContactsBinding
import com.bignerdranch.android.androidwithkotlin.toast

class ContactsFragment : Fragment(R.layout.fragment_contacts) {
    private val binding by viewBinding(FragmentContactsBinding::bind)

    private val permissionResult =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { result ->
            if (result) {
                getContacts()
            } else {
                toast(getString(R.string.need_permissions_to_read_contacts))
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
    }

    private fun checkPermission() {
        context?.let { notNullContext ->
            when (PackageManager.PERMISSION_GRANTED) {
                ContextCompat.checkSelfPermission(
                    notNullContext,
                    Manifest.permission.READ_CONTACTS
                ) -> {
                    getContacts()
                }
                else -> {
                    requestPermission()
                }
            }
        }
    }

    private fun requestPermission() {
        permissionResult.launch(android.Manifest.permission.READ_CONTACTS)
    }

    @SuppressLint("Range")
    private fun getContacts() {
        context?.let {
            val cursorWithContacts: Cursor? = it.contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC"
            )

            cursorWithContacts?.let { cursor ->
                for (i in 0..cursor.count) {
                    if (cursor.moveToPosition(i)) {
                        val name =
                            cursor.getString(
                                cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                            )
                        addView(name)
                    }
                }
            }
            cursorWithContacts?.close()
        }
    }

    private fun addView(textToShow: String) = with(binding) {
        containerForContacts.addView(AppCompatTextView(requireContext()).apply {
            text = textToShow
            textSize = resources.getDimension(R.dimen.main_container_text_size)
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = ContactsFragment()
    }
}