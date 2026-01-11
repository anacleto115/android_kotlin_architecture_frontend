
package com.example.lib_infrastructure.Implementations

import android.database.Cursor
import com.example.lib_domain_context.IParser
import com.example.lib_domain_entities.Models.PersonTypes

public class PersonTypesParser : IParser<PersonTypes>
{
    public override fun CreateEntity(ItemArray: Any): PersonTypes
    {
        var resultSet = ItemArray as Cursor;

        var entity = PersonTypes();
        entity.SetId(resultSet.getInt(0));
        entity.SetName(resultSet.getString(1));
        return entity;
    }

    public override fun ToEntity(data: HashMap<String, Any>): PersonTypes
    {
        var entity = PersonTypes();
        entity.SetId(data["Id"].toString().toInt());
        if (data.containsKey("Name"))
            entity.SetName(data["Name"].toString());
        return entity;
    }

    public override fun ToDictionary(entity: PersonTypes): HashMap<String, Any>
    {
        var data = HashMap<String, Any>();
        data["Id"] = entity.GetId().toString();
        if (entity.GetName() != null && entity.GetName() != "")
            data["Name"] = entity.GetName().toString();
        return data
    }

    public override fun Validate(entity: PersonTypes): Boolean
    {
        if (entity.GetName() == null || entity.GetName() == "")
            return false;
        return true;
    }
}