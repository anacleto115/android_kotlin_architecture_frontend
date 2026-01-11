
package com.example.lib_presentation_context.Core

import com.example.lib_domain_context.Enumerables
import com.example.lib_domain_context.IFactory
import com.example.lib_domain_context.IHelper
import com.example.lib_presentation_context.Implementations.Helpers.Communications.PersonTypesHelper

public class FactoryHelComContext : IFactory<IHelper>
{
    public override fun Get(data: HashMap<String, Any>): IHelper?
    {
        var type = data["Type"] as Enumerables.Types?;
        when (type)
        {
            Enumerables.Types.PersonTypes -> return PersonTypesHelper(data);
            else -> return null;
        }
    }
}