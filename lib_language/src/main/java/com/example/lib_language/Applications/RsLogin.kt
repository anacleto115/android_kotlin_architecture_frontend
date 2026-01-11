
package com.example.lib_language.Applications

public class RsLogin
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
        data!!["lbPassword"] = "Password";
        data!!["lbTitle"] = "LOGIN";
        data!!["lbUser"] = "User";
    }

    private fun Es()
    {
        data!!["lbPassword"] = "Contraseña"
        data!!["lbTitle"] = "INICIO DE SESIÓN"
        data!!["lbUser"] = "Usuario"
    }
}