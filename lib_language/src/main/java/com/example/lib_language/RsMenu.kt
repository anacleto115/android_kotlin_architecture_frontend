
package com.example.lib_language

public class RsMenu
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
        data!!["lbAccept"] = "Accept";
        data!!["lbAction"] = "ACTION";
        data!!["lbApp"] = "PersonsApp";
        data!!["lbApplications"] = "Applications";
        data!!["lbCancel"] = "Cancel";
        data!!["lbClean"] = "Clean";
        data!!["lbClose"] = "Close";
        data!!["lbEnter"] = "Enter";
        data!!["lbHome"] = "Home";
        data!!["lbMaintenances"] = "Maintenances";
        data!!["lbPersons"] = "Persons";
        data!!["lbPersonTypes"] = "Person types";
        data!!["lbNew"] = "New";
        data!!["lbSave"] = "Save";
        data!!["lbEdit"] = "Edit";
        data!!["lbSelect"] = "Load";
        data!!["lbInsert"] = "Create";
        data!!["lbUpdate"] = "Modify";
        data!!["lbDelete"] = "Delete";
        data!!["lbChoose"] = "CHOOSE";
        data!!["lbCompleted"] = "Completed";
    }

    private fun Es()
    {
        data!!["lbAccept"] = "Aceptar";
        data!!["lbAction"] = "ACCIÓN";
        data!!["lbApp"] = "PersonsApp";
        data!!["lbApplications"] = "Aplicaciones";
        data!!["lbCancel"] = "Cancelar";
        data!!["lbClean"] = "Limpiar";
        data!!["lbClose"] = "Cerrar";
        data!!["lbEnter"] = "Entrar";
        data!!["lbHome"] = "Inicio";
        data!!["lbMaintenances"] = "Mantenimientos";
        data!!["lbPersons"] = "Personas";
        data!!["lbPersonTypes"] = "Tipos de personas";
        data!!["lbNew"] = "Nuevo";
        data!!["lbSave"] = "Guardar";
        data!!["lbEdit"] = "Editar";
        data!!["lbSelect"] = "Cargar";
        data!!["lbInsert"] = "Crear";
        data!!["lbUpdate"] = "Modificar";
        data!!["lbDelete"] = "Borrar";
        data!!["lbChoose"] = "SELECCIONAR";
        data!!["lbCompleted"] = "Completado";
    }
}