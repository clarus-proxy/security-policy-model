package eu.clarussecure.datamodel.types;

import eu.clarussecure.datamodel.types.utils.TypesDAO;
import java.util.Arrays;
import java.util.Set;

public class Module {
    // The annotation "SerializedName" is required by Gson to correctly map
    // the name on the Json file with the enum value

    private String clarusModuleName;
    // The next variables are marked as transient to avoid being serialized by gson
    private static transient Set<String> installedModules = null;
    private static transient boolean initialized = false;

    public Module() {
        // NOTE - The invocation assumes the class has been initialized and there's at least 1 module
        // The default value: simply get the first
        this.clarusModuleName = null;
    }

    public Module(String name) {
        // this constructor assumes the name is correct. isValidModule should be called before calling this constructor
        this.clarusModuleName = name;
    }

    public String getClarusModuleName() {
        return this.clarusModuleName;
    }

    public void setClarusModuleName(String name) {
        this.clarusModuleName = name;
    }

    public static void initialize() {
        if (Module.initialized) {
            // If the class is already initialized, there's nothing to do
            return;
        }

        // Retrieve the list of registered modules
        TypesDAO dao = TypesDAO.getInstance();
        Module.installedModules = dao.loadModulesList();
        dao.deleteInstance();
        Module.initialized = true;
    }

    public static boolean isValidModule(String module) {
        // This method looks check if the given module is valid or not
        Set<String> modules = Module.getModules();

        for (String m : modules) {
            if (m.equals(module.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static Set<String> getModules() {
        // Initial check, has the class been initialized?
        if (!Module.initialized) {
            return null;
        }
        // Simply return the lsit of the installed modules
        return Module.installedModules;
    }

    public static String getModulesAsString() {
        // Initial check, has the class been initialized?
        if (!Module.initialized) {
            return null;
        }
        // This method "stringifies" the set of modules.
        // This function is intended to be show to the final user
        Set<String> modules = Module.getModules();

        return Arrays.toString(modules.toArray());
    }
}
