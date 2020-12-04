package com.example.govisitorsbook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_select.view.*


class SelectFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_select, container, false)

        root.btn_QR.setOnClickListener {
            findNavController().navigate(R.id.action_selectFragment_to_qrFragment)
        }

        root.btn_visit_list.setOnClickListener {
            findNavController().navigate(R.id.action_selectFragment_to_visitListFragment)
        }

        return root
    }


}