
# My Personal Library

This repository hosts a custom coding library, available through **GitHub Packages**. 

Follow the steps below to add this library as a dependency in a new Maven project.

## Prerequisites

- **Maven**: Ensure Maven is installed and configured in your system.
- **GitHub Personal Access Token (PAT)**: You'll need a PAT with `read:packages` permission to access the library.

## Steps to Use This Library in a New Maven Project

### 1. **Configure Maven to Authenticate with GitHub Packages**

You'll need to set up your Maven `settings.xml` to authenticate with GitHub using a Personal Access Token (PAT).

1. Locate your Maven `settings.xml` file:
    - On **Windows**: `C:\Users\<YourUsername>\.m2\settings.xml`
    - On **Linux/macOS**: `~/.m2/settings.xml`

    - If the file doesn't exist, create a new one.
      - **Windows**: `C:\Users\<YourUsername>\.m2\settings.xml`
      - **Linux/macOS**: `~/.m2/settings.xml`

       - _**Note**: If you're creating a new file, ensure it's saved as an XML file `settings.xml`_      
        


2. Add the following to your `settings.xml`:

   ```xml
   <settings>
       <servers>
           <server>
               <id>github</id>
               <username>YOUR_GITHUB_USERNAME</username>
               <password>${env.GITHUB_TOKEN}</password> <!-- Or replace with your PAT -->
           </server>
       </servers>
   </settings>
   ```

    - Replace `YOUR_GITHUB_USERNAME` with your GitHub username.
    - Set the `GITHUB_TOKEN` environment variable to your Personal Access Token (PAT). If not using environment variables, you can directly place the token in the `<password>` field.

### 2. **Add GitHub Packages as a Repository**

In your new project’s `pom.xml`, add the following repository entry to let Maven know where to find the library:

```xml
<repositories>
    <repository>
        <id>github</id>
        <name>GitHub AlekOmOm Packages</name>
        <url>https://maven.pkg.github.com/AlekOmOm/library</url>
    </repository>
</repositories>
```

### 3. **Add the Library as a Dependency**

To use the library in your project, include it as a dependency in your `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>com.Alek0m0m</groupId>
        <artifactId>library</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```

### 4. **Run Maven Install**

Once your `pom.xml` is set up, run the following command to ensure all dependencies are resolved, and your project is set up:

```bash
mvn clean install
```

### 5. **Use the Library in Your Code**

You can now use the classes and methods provided by the library in your project. Simply import the necessary classes, for example:

```java
import com.Alek0m0m.library.spring.web.mvc.BaseRESTController;

@RestController
public class MyController extends BaseRESTController<MyModel, Long, MyService>  {

   @Autowired
   public UserController(BaseService<MyModel, Long, BaseRepository<MyModel, Long>> service) {
      super(service);
   }
   // MyController now has auto implemented CRUD methods

   // Your code here:
   public void myCustomMethod() {
       service.myCustomServiceMethod(); // service variable is Autowired and Accessible in MyController
       service.findAll(); // findAll() inherited method BaseService class
   }
}
```

Now you can utilize the functionality provided by fx `BaseRESTController` from the Spring module of the library or other classes in your library.

---

## Troubleshooting

- **Error fetching dependency**: Ensure the Personal Access Token (PAT) in your `settings.xml` is valid and has `read:packages` permission.
- **Snapshot versions**: If you're using a snapshot version (`1.0-SNAPSHOT`), make sure you include the `<snapshotRepository>` tag in your `pom.xml` if needed.

---

Let me know if you want to add anything or further refine the guide!