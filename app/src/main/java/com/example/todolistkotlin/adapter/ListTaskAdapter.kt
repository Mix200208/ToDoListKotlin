package com.example.todolistkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistkotlin.R
import com.example.todolistkotlin.model.Task

class ListTaskAdapter(private val listener:(Task)->Unit, private val onDeleteItem: () -> Unit): RecyclerView.Adapter<ListTaskAdapter.TaskViewHolder>() {

  val listTask:MutableList<Task> = mutableListOf()


    fun setData(newListTask:MutableList<Task>){
        listTask.clear()
        listTask.addAll(newListTask)
        notifyDataSetChanged()

    }



    class TaskViewHolder(item: View):RecyclerView.ViewHolder(item) {

            val task: TextView = item.findViewById(R.id.taskName)
            val review: TextView = item.findViewById(R.id.taskReview)
            val but:ImageButton = item.findViewById(R.id.deleteButton)


            fun bindView(taskObj: Task,listener: (Task) -> Unit,onDeleteItem: () -> Unit){
                task.text = taskObj.task
                review.text = taskObj.review
                but.setOnClickListener { onDeleteItem() }
                itemView.setOnClickListener { listener(taskObj)}
            }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val convertView = inflater.inflate(R.layout.item_recyclerview,parent,false)
        return TaskViewHolder(convertView)
}

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bindView(listTask[position],listener,onDeleteItem)
    }

    override fun getItemCount(): Int {
        return listTask.size
    }


}