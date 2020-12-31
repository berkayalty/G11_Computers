package ISE308.PROJECT1.G11COMPUTERS

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class DisplayFragment(var computer: Computer) : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
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
           //call addEditFragment from there
        }

        view.findViewById<Button>(R.id.displayDeleteButton).setOnClickListener {
            it.startAnimation(scaleAnim)
            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("Delete!").setMessage("Do you really want to delete ${this.computer.brand}")
                .setPositiveButton(R.string.yes){ dialogInterface: DialogInterface, i: Int ->
                    (activity as MainActivity).deleteComputer(this.computer)
                }.setNegativeButton(R.string.no,null).show()
        }

        return view
    }


}