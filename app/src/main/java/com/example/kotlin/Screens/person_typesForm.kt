
package com.example.kotlin.Screens

import androidx.fragment.app.Fragment
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.example.kotlin.Adapters.PersonTypesListAdapter
import com.example.kotlin.MainActivity
import com.example.kotlin.R
import com.example.lib_domain_context.Enumerables.Loading
import com.example.lib_domain_context.IScreen
import com.example.lib_domain_entities.Models.PersonTypes
import com.example.lib_presentation_context.VwModels.PersonTypesViewModel
import com.example.lib_utilities.Utilities.CacheHelper
import java.util.concurrent.Executors

public class person_typesForm : Fragment(), IScreen
{
    private var btNew: Button? = null;
    private var btLoad: Button? = null;
    private var lbTitle: TextView? = null;
    private var listData: ListView? = null;
    private var _list: List<PersonTypes>? = null;
    private var iViewModel: PersonTypesViewModel;
    private var context: Context? = null;

    init
    {
        var data = HashMap<String, Any>();
        data["View"] = this;
        data["Architecture"] = CacheHelper.Get("Architecture") as Any;
        data["Context"] = MainActivity.context as Any;
        iViewModel = PersonTypesViewModel(data);
    }

    public override fun Loading(action: Loading) { }
    public override fun MoveFocus() { }
    public override fun Change(data: HashMap<String, Any>) { }

    public override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        var rootView: View = inflater.inflate(R.layout.person_types, container, false);
        LoadComponents(rootView);
        return rootView;
    }

    private fun LoadComponents(rootView: View)
    {
        context = rootView.context;

        lbTitle = rootView.findViewById<View>(R.id.lbTitle) as TextView;
        btLoad = rootView.findViewById<View>(R.id.btLoad) as Button;
        btNew = rootView.findViewById<View>(R.id.btNew) as Button;
        lbTitle!!.text = com.example.lib_language.Bundle.Get("RsPersonTypes", "lbTitle");
        btNew!!.text = com.example.lib_language.Bundle.Get("RsMenu", "lbNew");
        btLoad!!.text = com.example.lib_language.Bundle.Get("RsMenu", "lbSelect");
        btLoad!!.setOnClickListener { x: View? -> Load() };

        listData = rootView.findViewById<View>(R.id.list_data) as ListView;
        listData!!.onItemClickListener =
            OnItemClickListener { arg0, arg1, position, id -> };
        Load();
    }

    private fun Load()
    {
        var executor = Executors.newSingleThreadExecutor();
        var handler = Handler(Looper.getMainLooper());
        executor.execute(Runnable {
            var data = HashMap<String, Any>();
            var response = iViewModel.Select(data);
            if (response == null || response.containsKey("Error"))
                return@Runnable;
            _list = response["Entities"] as List<PersonTypes>?;
            handler.post { listData!!.adapter = PersonTypesListAdapter(context, _list!!) };
        });
    }
}