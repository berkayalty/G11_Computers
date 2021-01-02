package ise308.project1.g11computers

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class AddEditFragment(var computer : Computer) : Fragment(){
    private lateinit var brandEditText : EditText
    private lateinit var modelEditText : EditText
    private lateinit var modelYearEditText : EditText
    private lateinit var processorEditText : EditText
    private lateinit var ramSizeEditText : EditText
    private lateinit var screenSizeEditText : EditText
    private lateinit var graphicCardEditText : EditText
    private lateinit var storageCapacityEditText : EditText
    private lateinit var button : Button
    val editTextList = ArrayList<EditText>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val view = inflater.inflate(R.layout.fragment_add_edit, container, false)
        brandEditText= view.findViewById(R.id.brandEditText)
        modelEditText = view.findViewById(R.id.modelEditText)
        modelYearEditText = view.findViewById(R.id.modelYearEditText)
        processorEditText = view.findViewById(R.id.processorEditText)
        ramSizeEditText  = view. findViewById(R.id.ramSizeEditText)
        screenSizeEditText = view.findViewById(R.id.screenSizeEditText)
        graphicCardEditText = view.findViewById(R.id.graphicCardEditText)
        storageCapacityEditText = view.findViewById(R.id.storageCapacityEditText)
        button = view.findViewById(R.id.addButton)
        val scaleAnim = AnimationUtils.loadAnimation(context,R.anim.anim_button)
        scaleAnim.duration = 700
        addEditTextToList()
        if(!this.computer.brand.isNullOrEmpty()){
            setTextsOfEditTexts()
        }
        button.setOnClickListener {
            it.startAnimation(scaleAnim)
            checkEditTextsNullAndEditAdd()
        }

        return view
    }

    fun setTextsOfEditTexts(){
        brandEditText.setText(computer.brand)
        modelEditText.setText(computer.model)
        modelYearEditText.setText(computer.modelYear.toString())
        processorEditText.setText(computer.processor)
        ramSizeEditText.setText(computer.ramSize.toString())
        screenSizeEditText.setText(computer.screenSizeInc.toString())
        graphicCardEditText.setText(computer.graphicCard)
        storageCapacityEditText.setText(computer.storageCapacity.toString())
        button.setText(R.string.edit)
    }
    fun addEditTextToList(){
        editTextList.add(brandEditText)
        editTextList.add(modelEditText)
        editTextList.add(modelYearEditText)
        editTextList.add(processorEditText)
        editTextList.add(ramSizeEditText)
        editTextList.add(screenSizeEditText)
        editTextList.add(graphicCardEditText)
        editTextList.add(storageCapacityEditText)
    }
    fun checkEditTextsNullAndEditAdd(){
        var isAnyEmptyEditText = false;
        for(editText in editTextList){
            if(editText.text.toString().isEmpty()){
                isAnyEmptyEditText =true;
                editText.setBackgroundColor(Color.RED)
                Toast.makeText(context,"Please ${editText.hint}",Toast.LENGTH_SHORT).show()
                break;
            }
        }
        if(!isAnyEmptyEditText){
            if(this.computer.brand.isNullOrEmpty()){
                (activity as MainActivity).addNewComputer(Computer(brandEditText.text.toString(),modelEditText.text.toString(),
                    modelYearEditText.text.toString().toInt(),processorEditText.text.toString(),
                    ramSizeEditText.text.toString().toInt(),screenSizeEditText.text.toString().toDouble(),
                    graphicCardEditText.text.toString(),storageCapacityEditText.text.toString().toInt()
                ))
            }else{
                computer.brand = brandEditText.text.toString()
                computer.model = modelEditText.text.toString()
                computer.modelYear = modelYearEditText.text.toString().toInt()
                computer.processor = processorEditText.text.toString()
                computer.ramSize = ramSizeEditText.text.toString().toInt()
                computer.screenSizeInc = screenSizeEditText.text.toString().toDouble()
                computer.graphicCard =  graphicCardEditText.text.toString()
                computer.storageCapacity  = storageCapacityEditText.text.toString().toInt()
                (activity as MainActivity).editComputer(computer)
            }
            (activity as MainActivity).onBackPressed()
        }
    }

}