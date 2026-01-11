
package com.example.lib_domain_entities.Models

import com.example.lib_domain_context.IEntities

public class PersonTypes : IEntities, Cloneable
{
    private var _Id = 0;
    public fun SetId(v: Int) { _Id = v; }
    public fun GetId(): Int { return _Id;  }

    private var _Name: String? = null;
    public fun SetName(v: String?) { _Name = v; }
    public fun GetName(): String? { return _Name; }

    public override fun Get_Id(): Int { return _Id; }

    public override fun GetClone(): Any?
    {
        try
        {
            return super.clone();
        }
        catch (ex: CloneNotSupportedException)
        {
            return null;
        }
    }
}