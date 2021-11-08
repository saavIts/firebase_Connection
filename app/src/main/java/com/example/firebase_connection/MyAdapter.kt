package com.example.firebase_connection

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private var clienteList: ArrayList<Cliente>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        val cliente: Cliente = clienteList[position]
        holder.nombre.text = cliente.nombre
        holder.apellido.text = cliente.apellido
        holder.edad.text = cliente.edad.toString()
    }

    override fun getItemCount(): Int {
        return clienteList.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById<TextView>(R.id.nombreCliente)

        val apellido: TextView = itemView.findViewById<TextView>(R.id.apellidoCliente)

        val edad: TextView = itemView.findViewById<TextView>(R.id.edadCliente)
    }

}