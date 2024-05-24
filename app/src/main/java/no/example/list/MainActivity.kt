package no.example.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Item(private val text: String) {
    fun getText(): String {
        return text
    }
}

class AppDock(itemList: List<Item>) : RecyclerView.Adapter<AppDock.ViewHolder>() {
    private val itemList: List<Item> = itemList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Item = itemList[position]
        holder.textView.setText(item.getText())
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.item_text)
    }
}


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        populate()
    }

    private fun populate() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        val items = listOf(
            Item("item"),
            Item("item"),
            Item("item"),
            Item("item"),
            Item("item"),
            Item("item")
        )

        val layoutManager = GridLayoutManager(this, items.count())

        recyclerView.layoutManager = layoutManager
        // auto resize elements: https://stackoverflow.com/a/56207250
        val adapter: AppDock = AppDock(items)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true);
    }
}