package ise308.project1.g11computers

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class DisplayFragment(var computer: Computer) : Fragment(){
    companion object{
        const val TAG = "DisplayFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        Log.i(TAG, "onCreateView: Displayed computer is ${computer.brand}.")
        val view = inflater.inflate(R.layout.fragment_display, container, false)
        view.findViewById<TextView>(R.id.displayBrand).text = computer.brand
        view.findViewById<TextView>(R.id.displayModel).text = computer.model
        view.findViewById<TextView>(R.id.displayModelYear).text = computer.modelYear.toString()
        view.findViewById<TextView>(R.id.displayProcessor).text = computer.processor
        view.findViewById<TextView>(R.id.displayRamSize).text = computer.ramSize.toString()
        view.findViewById<TextView>(R.id.displayScreenSize).text = computer.screenSizeInc.toString()
        view.findViewById<TextView>(R.id.displayGraphicCard).text = computer.graphicCard
        view.findViewById<TextView>(R.id.displayStorage).text = computer.storageCapacity.toString()
        val scaleAnim = AnimationUtils.loadAnimation(context,R.anim.anim_button)
        scaleAnim.duration = 700


        view.findViewById<Button>(R.id.displayEditButton).setOnClickListener {
            it.startAnimation(scaleAnim)
            (activity as MainActivity).openAddEditFragment(computer)
        }

        view.findViewById<Button>(R.id.displayDeleteButton).setOnClickListener {
            it.startAnimation(scaleAnim)
            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("Delete!").setMessage("Do you really want to delete ${this.computer.brand}")
                .setPositiveButton(R.string.yes){ dialogInterface: DialogInterface, i: Int ->
                    (activity as MainActivity).deleteComputer(this.computer)
                    (activity as MainActivity).onBackPressed()
                }.setNegativeButton(R.string.no,null).show()
        }

        return view
    }


}