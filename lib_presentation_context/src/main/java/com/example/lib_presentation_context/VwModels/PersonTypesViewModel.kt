
package com.example.lib_presentation_context.VwModels

import com.example.lib_domain_context.Enumerables
import com.example.lib_domain_context.LogHelper
import com.example.lib_domain_entities.Models.PersonTypes
import com.example.lib_presentation_context.Core.VModel
import com.example.lib_presentation_core.Core.FactoryHelper
import com.example.lib_presentation_core.Interfaces.VwModels.IPersonTypesViewModel

public class PersonTypesViewModel(data: HashMap<String, Any>) : VModel<PersonTypes>(data), IPersonTypesViewModel
{
    public override fun Load(data: HashMap<String, Any>): HashMap<String, Any>
    {
        var data = data;
        try
        {
            data = super.Load(data);
            data["Type"] = Enumerables.Types.PersonTypes;
            if (!data.containsKey("IHelper")) IHelper =
                if (IHelper == null) FactoryHelper.Get(data) else IHelper;
        }
        catch (ex: Exception)
        {
            LogHelper.Log(ex);
        }
        return data;
    }

    public override fun CreateNew(): PersonTypes { return PersonTypes(); }
}