
package com.example.lib_language.Maintenances

public class RsHome
{
    private var data: HashMap<String, String>? = null;

    public constructor(language: String)
    {
        data = HashMap();
        Load(language);
    }

    public fun Get(): HashMap<String, String> { return data!!; }

    private fun Load(language: String)
    {
        when (language)
        {
            "Es" -> Es();
            else -> En();
        }
    }

    private fun En()
    {
        data!!["lbTitle"] = "Welcome";
    }

    private fun Es()
    {
        data!!["lbTitle"] = "Bienvenido";
    }
}