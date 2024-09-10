package cl.duoc.premier_meat

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AllUsersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_all_users)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listView = findViewById<ListView>(R.id.listViewUsers)
        val backButton: Button = findViewById(R.id.btnBack)

        val userList = MainActivity.UserData.userList.map { "${it.name} - ${it.email}" }
        println("User List Size: ${userList.size}")
        userList.forEach { println(it) }

        val adapter = object : ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, userList) {
            override fun getView(position: Int, convertView: android.view.View?, parent: android.view.ViewGroup): android.view.View {
                val view = super.getView(position, convertView, parent)
                val textView = view.findViewById<TextView>(android.R.id.text1)

                textView.setTextColor(ContextCompat.getColor(this@AllUsersActivity, R.color.accent_gold))
                textView.textSize = 20F
                return view
            }
        }

        listView.adapter = adapter

        backButton.setOnClickListener {
            val intent = Intent(this, AdminMenuActivity::class.java)
            startActivity(intent)
        }
    }
}
