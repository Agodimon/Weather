package com.bignerdranch.android.androidwithkotlin.framework.ui.contacts

import android.app.Activity
import android.app.Instrumentation
import android.content.ActivityNotFoundException
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bignerdranch.android.androidwithkotlin.R
import com.bignerdranch.android.androidwithkotlin.databinding.FragmentContactsBinding
import android.Manifest
import android.database.Cursor
import android.provider.ContactsContract
import androidx.appcompat.widget.AppCompatTextView

class ContactsFragment : Fragment() {
    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding!!

    private val permissionResult =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { result ->
            if (result) {
                getContacts()
            } else {
                Toast.makeText(
                    context,
                    getString(R.string.need_permissions_to_read_contacts),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
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

    private fun getContacts() {
        context?.let {
            // ???????????????????? ???????????? ???? ?????????????????? ?????????????????? ?? ???????????????? ?????????? ?? ???????? Cursor'??
            val cursorWithContacts: Cursor? = it.contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC"
            )

            cursorWithContacts?.let { cursor ->
                for (i in 0..cursor.count) {
                    // ?????????????????? ???? ?????????????? ?? Cursor'??
                    if (cursor.moveToPosition(i)) {
                        // ?????????? ???? Cursor'?? ?????????????? ?? ????????????
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