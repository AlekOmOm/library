package com.Alek0m0m.library.core.utils.file.md;

public class MD {

    private MDFile mdFile;

    // ------------------- CRUD MDFile -------------------
    public MDFile createMDFile(String exportPath) {
        return new MDFile(exportPath);
    }

    public MDFile createMDFile() {
        String exportPath = "/C:/Users/User/Desktop/"; // default export path
        return createMDFile(exportPath);
    }


}

