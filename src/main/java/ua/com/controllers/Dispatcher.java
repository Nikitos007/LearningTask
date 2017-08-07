package ua.com.controllers;

import ua.com.controllers.impl.*;

import java.util.HashMap;
import java.util.Map;

public class Dispatcher {

    private static Map<String, Controller> commands;

    {
        commands = new HashMap<>();
        // for department
        commands.put("/controller/viewAllDepartment", new ViewAllDepartmentCommand());
        commands.put("/controller/viewDepartment", new ViewDepartmentCommand());
        commands.put("/controller/viewRegistrationDepartmentForm", new ViewRegistrationDepartmentFormCommand());
        commands.put("/controller/saveDepartment", new SaveDepartmentCommand());
        commands.put("/controller/deleteDepartment", new DeleteDepartmentCommand());
        // for emoloyee
        commands.put("/controller/viewRegistrationEmployeeForm", new ViewRegistrationEmployeeFormCommand());
        commands.put("/controller/saveEmployee", new SaveEmployeeCommand());
        commands.put("/controller/deleteEmployee", new DeleteEmployeeCommand());
    }

    public Controller get(String commandName) {
        if (commands.containsKey(commandName)) {
            return commands.get(commandName);
        }
        return commands.get("/controller/viewAllDepartment");
    }
}
