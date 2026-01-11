
package com.example.lib_presentation_core.Core

import com.example.lib_domain_context.IFactory
import com.example.lib_domain_context.IHelper

public class FactoryHelper
{
    companion object
    {
        public var IFactoryHelper: IFactory<IHelper>? = null;

        public fun Get(data: HashMap<String, Any>): IHelper?
        {
            if (IFactoryHelper == null)
                return null;

            return IFactoryHelper!!.Get(data);
        }
    }
}