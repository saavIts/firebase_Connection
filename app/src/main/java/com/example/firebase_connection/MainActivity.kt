package com.example.firebase_connection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var clienteArrayList: ArrayList<Cliente>
    private lateinit var myAdapter: MyAdapter
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        clienteArrayList = arrayListOf()

        myAdapter = MyAdapter(clienteArrayList)

        recyclerView.adapter = myAdapter

        EventChangeListener()
    }

    private fun EventChangeListener(){
        db = FirebaseFirestore.getInstance()
        db.collection("clientes")
            .addSnapshotListener(object: EventListener<QuerySnapshot>{
                override fun onEvent(
                    value: QuerySnapshot?,
                    error: FirebaseFirestoreException?
                ){
                    if(error != null){
                        Log.e("Firestore Error", error.message.toString())
                        return
                    }
                    for(dc: DocumentChange in value?.documentChanges!!){
                        if(dc.type == DocumentChange.Type.ADDED){
                            clienteArrayList.add(dc.document.toObject(Cliente::class.java))
                        }
                    }
                    myAdapter.notifyDataSetChanged()
                }
            })
    }
}