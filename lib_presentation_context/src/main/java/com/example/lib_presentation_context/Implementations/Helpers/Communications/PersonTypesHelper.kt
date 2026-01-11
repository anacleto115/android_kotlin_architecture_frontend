
package com.example.lib_presentation_context.Implementations.Helpers.Communications

import com.example.lib_communication_core.Core.FactoryCommunication
import com.example.lib_domain_context.Enumerables
import com.example.lib_domain_entities.Models.PersonTypes
import com.example.lib_infrastructure.Implementations.PersonTypesParser
import com.example.lib_presentation_context.Core.HelperCommunication
import com.example.lib_presentation_core.Interfaces.Helpers.IPersonsHelper

public class PersonTypesHelper(data: HashMap<String, Any>) : HelperCommunication<PersonTypes>(), IPersonsHelper
{
    public override fun Load(_data: HashMap<String, Any>): HashMap<String, Any>
    {
        var data = _data;
        data = super.Load(data);
        IParser = PersonTypesParser();
        data["Type"] = Enumerables.Types.PersonTypes;
        if (!data.containsKey("ICommunication"))
            ICommunication = if (ICommunication == null) FactoryCommunication.Get(data) else ICommunication;
        data.remove("Type");
        return data;
    }
}