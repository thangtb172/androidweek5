//package listRestaurant
//
//import android.os.Bundle
//import android.view.*
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.widget.Toolbar
//import androidx.recyclerview.widget.GridLayoutManager
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.week3.R
//import kotlinx.android.synthetic.main.activity_restaurant_list.*
//import kotlinx.android.synthetic.main.activity_restaurant_list.view.*
//
//class RecycleRestaurantActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
//        setContentView(R.layout.activity_restaurant_list)
//        val toolbar: Toolbar = findViewById<View>(R.id.menuOption) as Toolbar
//        setSupportActionBar(toolbar)
//        supportActionBar?.setDisplayShowTitleEnabled(false)
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_option,menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.itemMenuLinear ->{
//               Toast.makeText(this,"Selected linear layout",Toast.LENGTH_SHORT).show()
//                recycle_view.layoutManager = LinearLayoutManager(this)
//            }
//            R.id.itemMenuGrid ->{
//                Toast.makeText(this,"Selected grid layout", Toast.LENGTH_SHORT ).show()
//                recycle_view.layoutManager = GridLayoutManager(this, 3)
//            }
//        }
//        return true
//    }
//
//
//}