package ru.stqa.mantis.manager;


import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JamesCliHelper extends HelperBase {

    public JamesCliHelper(ApplicationManager manager) {
        super(manager);
    }

    public void addUser(String email, String password) throws IOException {
        // Формируем команду
        String[] command = {"java","-cp","\"james-server-jpa-app.lib/*\"","org.apache.james.cli.ServerCmd","AddUser",email,password};

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.directory(new File(manager.property("james.workDir")));

        DefaultExecutor executor = new DefaultExecutor();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ByteArrayOutputStream error = new ByteArrayOutputStream();
        executor.setStreamHandler(new PumpStreamHandler(output, error));

        Process process = processBuilder.start();

        try (java.io.InputStream inputStream = process.getInputStream();
             java.io.InputStream errorStream = process.getErrorStream()) {
            output.write(inputStream.readAllBytes());
            error.write(errorStream.readAllBytes());
            int exitCode = process.waitFor();
            System.out.println("Command: " + String.join(" ", command));
            System.out.println("Exit code: " + exitCode);
            System.out.println("Output:\n" + output.toString("UTF-8"));

            if (!error.toString("UTF-8").isEmpty()) {
                System.err.println("Errors:\n" + error.toString("UTF-8"));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Command interrupted", e);
        }
    }

}
