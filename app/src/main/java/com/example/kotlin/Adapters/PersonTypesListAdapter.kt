
package com.example.kotlin.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.kotlin.R
import com.example.lib_domain_entities.Models.PersonTypes
import com.example.lib_language.Bundle

public class PersonTypesListAdapter(context: Context?, objects: List<PersonTypes>) : ArrayAdapter<Any?>(context!!, 0, objects)
{
    private var ListData: List<PersonTypes>;

    init
    {
        ListData = objects;
    }

    public override fun getView(position: Int, _convertView: View?, parent: ViewGroup): View
    {
        var convertView = _convertView;
        if (convertView == null)
        {
            var inflater = parent.context.
            getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater;
            convertView = inflater.inflate(R.layout.dt_person_types, null);
        }

        var lbName = convertView!!.findViewById<View>(R.id.lbName) as TextView;
        lbName.text = Bundle.Get("RsPersonTypes", "clName");

        var item: PersonTypes = ListData[position];

        var name = convertView.findViewById<View>(R.id.name) as TextView;
        name.text = item.GetName();
        return convertView!!;
    }
}