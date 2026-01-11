
package com.example.lib_language.Maintenances

public class RsPersonTypes
{
    private var data: HashMap<String, String>? = null;

    public constructor(language: String)
    {
        data = HashMap();
        Load(language);
    }

    public fun Get(): HashMap<String, String> { return data!! }

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
        data!!["clName"] = "NAME";
        data!!["lbName"] = "Name*";
        data!!["lbSubTitle"] = "Person type";
        data!!["lbTitle"] = "Person types";
    }

    private fun Es()
    {
        data!!["clName"] = "NOMBRE";
        data!!["lbName"] = "Nombre*";
        data!!["lbSubTitle"] = "Tipo de persona";
        data!!["lbTitle"] = "Tipos de personas";
    }
}