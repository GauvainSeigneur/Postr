package com.seigneur.gauvain.postr.base.pagedlist

import android.view.View
import android.widget.Button
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.seigneur.gauvain.postr.R

@EpoxyModelClass(layout = R.layout.layout_next_error)
abstract class NextErrorEpoxyModel : EpoxyModelWithHolder<NextErrorEpoxyModel.Holder>() {

    @EpoxyAttribute
    var clickListener: View.OnClickListener? = null

    @EpoxyAttribute
    var errorStr: String? = null

    override fun bind(holder: Holder) {
        holder.errorTextView.text = errorStr
        holder.retryButton.setOnClickListener {
            clickListener?.onClick(it)
        }
    }

    class Holder : EpoxyHolder() {
        lateinit var errorTextView: TextView
        lateinit var retryButton: Button
        override fun bindView(itemView: View) {
            errorTextView = itemView.findViewById(R.id.error_tv)
            retryButton = itemView.findViewById(R.id.retryButton)
        }
    }
}
