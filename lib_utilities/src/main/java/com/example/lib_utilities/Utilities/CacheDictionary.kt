
package com.example.lib_utilities.Utilities

public class CacheDictionary : ICacheHelper
{
    private var data: HashMap<String, Any>? = null;

    public override fun Add(key: String, value: Any)
    {
        Instance();
        data!![key] = value;
    }

    public override fun Contains(key: String): Boolean
    {
        Instance();
        return data!!.containsKey(key);
    }

    public override fun Get(key: String): Any?
    {
        Instance();
        if (!Contains(key))
            return null;
        return data!![key];
    }

    public override fun Instance()
    {
        if (data != null)
            return;
        data = HashMap();
    }

    public override fun Remove(key: String)
    {
        Instance();
        if (!Contains(key))
            return;
        data!!.remove(key);
    }
}