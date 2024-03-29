package hu.ait.aitforum

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import hu.ait.aitforum.adapter.PostsAdapter
import hu.ait.aitforum.data.Post
import kotlinx.android.synthetic.main.app_bar_forum.*

class ForumActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var postsAdapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forum)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener { view ->
            startActivity(Intent(this@ForumActivity, CreateActivity::class.java))
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        postsAdapter = PostsAdapter(this,
            FirebaseAuth.getInstance().currentUser!!.uid)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        recyclerPosts.layoutManager = layoutManager

        recyclerPosts.adapter = postsAdapter

        initPosts()
    }


    fun initPosts() {
        val db = FirebaseFirestore.getInstance()

        val query = db.collection("posts")

        query.addSnapshotListener(
            object : EventListener<QuerySnapshot> {
                override fun onEvent(querySnapshot: QuerySnapshot?, e: FirebaseFirestoreException?) {
                    if (e!=null) {
                        Toast.makeText(this@ForumActivity, "Error: ${e.message}",
                            Toast.LENGTH_LONG).show()
                        return
                    }

                    for (docChange in querySnapshot?.getDocumentChanges()!!) {
                        if (docChange.type == DocumentChange.Type.ADDED) {
                            val post = docChange.document.toObject(Post::class.java)
                            postsAdapter.addPost(post, docChange.document.id)
                        } else if (docChange.type == DocumentChange.Type.REMOVED) {

                            postsAdapter.removePostByKey(docChange.document.id)

                        } else if (docChange.type == DocumentChange.Type.MODIFIED) {

                        }
                    }

                }
            }
        )
    }




    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.forum, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_logout -> {
                FirebaseAuth.getInstance().signOut()

                finish() // close this activity
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
