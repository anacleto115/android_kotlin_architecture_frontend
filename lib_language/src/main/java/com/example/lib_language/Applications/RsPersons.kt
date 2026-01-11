
package com.example.lib_language.Applications

public class RsPersons
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
        data!!["clBorn"] = "BORN";
        data!!["clFile"] = "FILE";
        data!!["clName"] = "NAME";
        data!!["clSSN"] = "SSN";
        data!!["clState"] = "STATE";
        data!!["clType"] = "TYPE";
        data!!["lbBorn"] = "Born*";
        data!!["lbFile"] = "File";
        data!!["lbName"] = "Name*";
        data!!["lbSSN"] = "SSN*";
        data!!["lbState"] = "State";
        data!!["lbSubTitle"] = "Person";
        data!!["lbTitle"] = "Persons";
        data!!["lbType"] = "Type*";
    }

    private fun Es()
    {
        data!!["lbTitle"] = "Bienvenido";
        data!!["clBorn"] = "FECHA N.";
        data!!["clFile"] = "ARCHIVO";
        data!!["clName"] = "NOMBRE";
        data!!["clSSN"] = "CEDULA";
        data!!["clState"] = "ESTADO";
        data!!["clType"] = "TIPO";
        data!!["lbBorn"] = "Fecha n*";
        data!!["lbFile"] = "Archivo";
        data!!["lbName"] = "Nombre*";
        data!!["lbSSN"] = "Cedula*";
        data!!["lbState"] = "Estado";
        data!!["lbSubTitle"] = "Persona";
        data!!["lbTitle"] = "Personas";
        data!!["lbType"] = "Tipo*";
    }
}