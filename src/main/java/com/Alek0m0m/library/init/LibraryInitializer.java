package main.java.com.Alek0m0m.library.init;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LibraryInitializer {

    // Method to create the GitHub Actions YAML file
    public void createGithubActionFile() {
        try {
            // Define the directory structure
            File workflowsDir = new File(".github/workflows");
            if (!workflowsDir.exists()) {
                workflowsDir.mkdirs();
                System.out.println("Created directory: .github/workflows");
            }

            // Define the content for the YAML file
            String yamlContent = """
                name: Update Submodules

                on:
                  push:
                    branches:
                      - main

                jobs:
                  update_submodules:
                    runs-on: ubuntu-latest
                    steps:
                    - name: Checkout repository
                      uses: actions/checkout@v2
                      with:
                        submodules: true

                    - name: Update submodules
                      run: |
                        git submodule update --remote --merge
                        git add .
                        git commit -m "Update submodules"
                        git push
                """;

            // Write the content to the update_submodules.yml file
            FileWriter fileWriter = new FileWriter(".github/workflows/update_submodules.yml");
            fileWriter.write(yamlContent);
            fileWriter.close();
            System.out.println("GitHub Actions YAML file created at: .github/workflows/update_submodules.yml");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to run Git commands
    public void runGitCommands() {
        try {
            // Initialize the submodule
            System.out.println("Running Git submodule add command...");
            runCommand("git submodule add https://github.com/Alek0m0m/library.git libs/library");

            // Initialize and update the submodule
            System.out.println("Running Git submodule update command...");
            runCommand("git submodule update --init --recursive");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to run shell commands
    private void runCommand(String command) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", command);

        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        System.out.println("\nExited with code : " + exitCode);
    }

    // Main method to trigger the initialization process
    public static void main(String[] args) {
        LibraryInitializer initializer = new LibraryInitializer();
        initializer.runGitCommands();          // Run Git commands
        initializer.createGithubActionFile();  // Create the GitHub Actions YAML file
    }
}
