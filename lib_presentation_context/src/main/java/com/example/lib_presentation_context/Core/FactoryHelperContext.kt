
package com.example.lib_presentation_context.Core

import com.example.lib_domain_context.Enumerables
import com.example.lib_domain_context.IFactory
import com.example.lib_domain_context.IHelper

public class FactoryHelperContext : IFactory<IHelper>
{
    private var FactoryHelAppContext = FactoryHelAppContext();
    private var FactoryHelComContext = FactoryHelComContext();

    public override fun Get(data: HashMap<String, Any>): IHelper?
    {
        if (data.containsKey("Architecture") &&
            data["Architecture"] as Enumerables.Architecture? === Enumerables.Architecture.StandAlone)
            return FactoryHelAppContext.Get(data);
        return FactoryHelComContext.Get(data);
    }
}