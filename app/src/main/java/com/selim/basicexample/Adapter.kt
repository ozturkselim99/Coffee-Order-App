package com.selim.basicexample

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.person_row.view.*

class Adapter(val list:ArrayList<MainActivity.Person>):RecyclerView.Adapter<Adapter.PersonVH>() {
    class PersonVH(itemView: View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonVH {
      val itemView=LayoutInflater.from(parent.context).inflate(R.layout.person_row,parent,false)
        return PersonVH(itemView)
    }

    override fun onBindViewHolder(holder: PersonVH, position: Int) {
        holder.itemView.txtName.text=list.get(position).personName
        holder.itemView.txtSurname.text=list.get(position).personSurname
        holder.itemView.deletePerson.setOnClickListener{
            removeItem(position)
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun removeItem(position: Int)
    {
        var person=list.get(position)
        list.remove(person)
        notifyDataSetChanged()
    }

}


