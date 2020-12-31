package ISE308.PROJECT1.G11COMPUTERS

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ComputerAdapter(var context: Context, var activity: Activity, var computerList : ArrayList<Computer>) : RecyclerView.Adapter<ComputerAdapter.ViewHolder>() {
    private val inflater: LayoutInflater
    init {
        inflater = LayoutInflater.from(this.context)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.card_view_computer,parent,false)
        return ViewHolder(view, activity)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(this.computerList.get(position))
    }

    override fun getItemCount(): Int{
        return this.computerList.size
    }

    inner class ViewHolder(itemView: View, activity: Activity) : RecyclerView.ViewHolder(itemView){
        var brandTextView : TextView
        var modelTextView :TextView
        var modelYearTextView :TextView
        lateinit var computerObj : Computer
        val onItemSelectedListener = View.OnClickListener {
            (activity as MainActivity).openDisplayFragment(computerObj)
        }
        init{
            brandTextView = itemView.findViewById(R.id.cardComputerBrand)
            modelTextView = itemView.findViewById(R.id.cardComputerModel)
            modelYearTextView = itemView.findViewById(R.id.cardComputerModelYear)
            itemView.setOnClickListener(onItemSelectedListener)
        }

        fun setData(computer: Computer){
            computerObj = computer
            brandTextView.text = computer.brand
            modelTextView.text = computer.model
            modelYearTextView.text = computer.modelYear.toString()
        }

    }


}