
package com.example.kotlin.Screens

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.example.kotlin.R

public class main_windowForm : Fragment()
{
    private val ListData: List<DrawerItems> = object : ArrayList<DrawerItems>() {
        init
        {
            add(DrawerItems("PersonTypes", 0));
        }
    };

    public override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        var rootView: View = inflater.inflate(R.layout.main_window, container, false);
        LoadComponents(rootView);
        return rootView;
    }

    private fun LoadComponents(rootView: View)
    {
        Companion.fragmentManager = fragmentManager;
        ShowFragment(person_typesForm());
        var drawerList = rootView.findViewById<View>(R.id.left_drawer) as ListView;
        drawerList.adapter = DrawerListAdapter(rootView.context, ListData);
        drawerList.onItemClickListener =
            OnItemClickListener { arg0, arg1, position, id -> ChangeScreen(position) };
    }

    public fun ChangeScreen(position: Int)
    {
        var fragment: Fragment? = null;
        when (ListData[position].name)
        {
            else -> fragment = person_typesForm();
        }
        ShowFragment(fragment);
    }

    private inner class DrawerItems(var name: String, var iconId: Int);

    private inner class DrawerListAdapter(context: Context?, objects: List<DrawerItems>) : ArrayAdapter<Any?>(context!!, 0, objects)
    {
        private var ListData: List<DrawerItems>;

        init
        {
            this.ListData = objects;
        }

        public override fun getView(position: Int, convertView: View?, parent: ViewGroup): View
        {
            var convertView = convertView;
            if (convertView == null)
            {
                var inflater =
                    parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater;
                convertView = inflater.inflate(R.layout.drawer_items, null);
            }

            var icon = convertView!!.findViewById<View>(R.id.item_icon) as ImageView;
            var name = convertView.findViewById<View>(R.id.item_name) as TextView;

            var item = ListData[position];
            if (item.iconId != 0)
                icon.setImageResource(item.iconId);
            name.text = com.example.lib_language.Bundle.Get("RsMenu", "lb" + item.name);

            return convertView;
        }
    }

    companion object
    {
        private var fragmentManager: FragmentManager? = null;

        public fun ShowFragment(fragment: Fragment?)
        {
            fragmentManager!!.beginTransaction()
                .replace(R.id.content, fragment!!)
                .commit();
        }
    }
}