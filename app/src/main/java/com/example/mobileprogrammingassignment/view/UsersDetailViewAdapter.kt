package com.pinkdot.app.presentation.views.RetailUserVendorPostAd.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.example.mobileprogrammingassignment.R
import com.example.mobileprogrammingassignment.database.entity.UserDataEt

class UsersDetailViewAdapter(
    private val usersList: ArrayList<UserDataEt>,
) : RecyclerView.Adapter<UsersDetailViewAdapter.UsersDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersDetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_view_layout, parent, false)
        return UsersDetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersDetailViewHolder, position: Int) {
        val user = usersList[position]
        holder.tvName.setText(user.name)
        holder.tvEmail.setText(user.email)
        holder.tvGender.setText(user.gender)
        holder.tvStatus.setText(user.status)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    // View Holder Class to handle Recycler View.
    inner class UsersDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView
        val tvEmail: TextView
        val tvGender: TextView
        val tvStatus: TextView

        init {
            tvName = itemView.findViewById(R.id.tvName)
            tvEmail = itemView.findViewById(R.id.tvEmail)
            tvGender = itemView.findViewById(R.id.tvGender)
            tvStatus = itemView.findViewById(R.id.tvStatus)
        }
    }
}