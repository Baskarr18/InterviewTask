package com.example.root.jsonparsingwithrealm.customeradapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.root.jsonparsingwithrealm.CustomerListIterface;
import com.example.root.jsonparsingwithrealm.R;

/**
 * Created by root on 6/8/17.
 */

public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView nameTextView, phoneNumberTextView;
    LinearLayout itemParentLinearLayout;
    CustomerListIterface customerListIterface;

    public ListViewHolder(View itemView, CustomerListIterface customerListIterface) {
        super(itemView);
        this.nameTextView = (TextView) itemView.findViewById(R.id.name_textView);
        this.phoneNumberTextView = (TextView) itemView.findViewById(R.id.phone_number_textView);
        this.itemParentLinearLayout = (LinearLayout) itemView.findViewById(R.id.item_parent_view);
        this.customerListIterface = customerListIterface;

        itemParentLinearLayout.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        customerListIterface.onItemClick(v, getPosition());

    }
}
