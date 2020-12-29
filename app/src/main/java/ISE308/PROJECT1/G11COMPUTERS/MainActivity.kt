package ISE308.PROJECT1.G11COMPUTERS

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.FragmentManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(){
    val JSON_FILE_NAME = "COMPUTERS_JSON"
    private lateinit var fragmentContainer : FrameLayout
    private lateinit var fragmentManager: FragmentManager
    lateinit var computersList : ArrayList<Computer> //computerList for reach from every fragments
    private lateinit var jsonSerializer: JSONSerializer  //don't use JSONSerializer object from fragments
    val homeFragment = HomeFragment()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        jsonSerializer = JSONSerializer(this.JSON_FILE_NAME,applicationContext)
        computersList = jsonSerializer.load()
        fragmentManager = supportFragmentManager
        fragmentContainer = findViewById(R.id.fragmentContainer)
        fragmentManager.beginTransaction()
            .add(fragmentContainer.id,homeFragment,"HOME_FRAGMENT_TAG").commit()
        findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener {
            openAddEditFragment(Computer())
        }
    }
    //this function called from floating action button and also called from edit button in the 'DisplayFragment'
    fun openAddEditFragment(computer: Computer){
        val addFragment = AddEditFragment(computer)
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.anim_enter,R.anim.anim_exit)
            .replace(fragmentContainer.id,addFragment,"ADD_FRAGMENT_TAG")
            .addToBackStack(null).commit()
    }
    //call this function from 'Recycler View Custom Adapter for Computer's View.onClickListener()'
    fun openDisplayFragment(computer : Computer){
        val displayFragment = DisplayFragment(computer)
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.anim_enter,R.anim.anim_exit)
            .replace(fragmentContainer.id,displayFragment,"DISPLAY_FRAGMENT_TAG")
            .addToBackStack(null).commit()
    }

    //call this function from 'AddEditFragment'
    fun addNewComputer(computer:Computer){
        computersList.add(computer)
        jsonSerializer.save(computersList)
    }

    //call this function from 'DisplayFragment'
    fun deleteComputer(computer:Computer){
        computersList.remove(computer)
        jsonSerializer.save(computersList)
    }

    //call this function from 'DisplayFragment'
    fun editComputer(computer : Computer){
        val changedComputer = computersList.find {
            it.ID == computer.ID
        }
        val indexOfChangedComputer = computersList.indexOf(changedComputer)
        computersList.removeAt(indexOfChangedComputer)
        computersList.add(indexOfChangedComputer,computer)
        jsonSerializer.save(computersList)
    }



}