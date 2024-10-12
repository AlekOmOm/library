import com.Alek0m0m.library.utils.file.md.*;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.*;

public class MDFileTest {
    public static void main(String[] args) {
        testTOC();
        testRelations();
    }

    private static void testTOC() {
        // test of:
            // 1. createMDFile()
            // 2. createTOC()

        MD md = new MD();
        MDFile file = md.createMDFile();

        Map<String, List<String>> headersAndBulletPoints = new LinkedHashMap<>();
        headersAndBulletPoints.put("Definition of Git Submodule", new ArrayList<>());
        headersAndBulletPoints.put("Utility of Git Submodules", new ArrayList<>(List.of("Utility in General", "Utility for Private Libraries")));
        headersAndBulletPoints.put("Steps for Cloning repository, which uses Git Submodules", new ArrayList<>());

        file.getContent().createTOC(headersAndBulletPoints);

        System.out.println(file);
        System.out.println();


        // TOC Example:
    /*
    ## Table of Contents
    1. [Definition of Git Submodule](#definition-of-git-submodule)
    2. [Utility of Git Submodules](#utility-of-git-submodules)
       - [Utility in General](#utility-in-general)
       - [Utility for Private Libraries](#utility-for-private-libraries)
    3. [[Steps for Cloning repository, which uses Git Submodules]]
     */
    }

    private static void testRelations() {
        // test of:
            // 1. setRelations()
        MD md = new MD();
        MDFile file = md.createMDFile();

        file.getContent().setRelations(new ArrayList<>(List.of("_030 Technology MOC", "Git", "Private Libraries")));

        System.out.println(file);
        System.out.println();
    }


}
