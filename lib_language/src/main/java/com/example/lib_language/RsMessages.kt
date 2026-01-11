
package com.example.lib_language

public class RsMessages
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
        data!!["lbActive"] = "Active";
        data!!["lbDeleteEntity"] = "Do you want to delete the selected item?";
        data!!["lbExists"] = "The item is already exist.";
        data!!["lbInactive"] = "Inactive";
        data!!["lbLoading"] = "Loading...";
        data!!["lbMessage"] = "Message";
        data!!["lbNoExist"] = "The information no longer exists.";
        data!!["lbSelectItem"] = "Please, select a item in the list.";
        data!!["lbSqlError"] = "Please, contact to your administrator.";
        data!!["lbThisIsInUse"] = "This item is in used, you cannot delete it.";
        data!!["lbTryAgain"] = "Please, try again.";
        data!!["lbMissingInfo"] = "Please check, there is missing information.";
    }

    private fun Es()
    {
        data!!["lbActive"] = "Activo";
        data!!["lbDeleteEntity"] = "¿Desea eliminar el elemento seleccionado?";
        data!!["lbExists"] = "El elemento ya existe.";
        data!!["lbInactive"] = "Inactivo";
        data!!["lbLoading"] = "Cargando...";
        data!!["lbMessage"] = "Mensaje";
        data!!["lbNoExist"] = "La información ya no existe.";
        data!!["lbSelectItem"] = "Por favor, seleccione un elemento de la lista.";
        data!!["lbSqlError"] = "Por favor, póngase en contacto con su administrador.";
        data!!["lbThisIsInUse"] = "Este elemento está en uso, no puede eliminarlo.";
        data!!["lbTryAgain"] = "Por favor, intente de nuevo.";
        data!!["lbMissingInfo"] = "Por favor verifique, falta información.";
    }
}