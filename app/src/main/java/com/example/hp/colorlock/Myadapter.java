package com.example.hp.colorlock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class Myadapter extends ArrayAdapter<Network> implements View.OnClickListener {
    private ArrayList<Network> dataSet;
    Context context;
                            //View look up cache
    private static class ViewHolder{
        TextView network_name;
        TextView network_id;
    }
    public Myadapter(ArrayList<Network> dataSet,Context context){
        super(context,R.layout.list_view_row_item,dataSet);
        this.dataSet=dataSet;
        this.context=context;
    }
    @Override
    public void onClick(View view){
        ///to be added later........

    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //Get data item for this position
        Network network=getItem(position);
        //Check if an existing view is being reused ,otherwise inflate the view
        ViewHolder viewHolder;//view look up cache stored in tag
        final View result;
        if(convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.list_view_row_item,parent,false);
            viewHolder.network_id=convertView.findViewById(R.id.network_id);
            viewHolder.network_name=convertView.findViewById(R.id.user_name);
            result=convertView;
            result.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
            result=convertView;
        }
        viewHolder.network_name.setText(network.getNetwork_name());
        viewHolder.network_id.setText(network.getNetwork_id());
        return  result;
    }




}

