Here's an updated version of your README with the necessary changes and improvements:

---

# Library with hopefully Usable Code Snippets

This repository hosts a custom coding library, available through **GitHub Packages**.

## Languages

- **Java**
- Coming Soon (hopefully):
    - **JavaScript**
    - **Python**

## Required Frameworks and Tools

- **Spring**
- **Maven**
- **JPA**

## Library Modules

- [Spring](docs/spring-module.md)
- [JPA](docs/jpa-module.md)
- [Core](docs/core-module.md)

### Basic Structure

- **Spring**
    - **Web.MVC**:
        - Supports MVC, REST, and Data JPA with **layer autowiring**.
        - **BaseController**: Provides main **HTTP mappings** for CRUD operations and service autowiring.
        - **BaseService**: Implements **error handling** for CRUD flow between Controller and Repository.
        - **BaseRepository**: Utilizes **JPA** and integrates with **BaseEntity** (from the JPA module).

- **JPA**:
    - For JPA entities and relationships.
    - **BaseEntity**: A JPA entity with fields for **ID and Name**, along with standard `get`, `set`, and `toString()` methods.

- **Core**:
    - Contains general Java utilities.
    - **File**: For handling Markdown (MD) files.
    - **WordHandler**: For processing, identifying, cleaning, and appending words.

## How to Use This Library in a New Maven Project

### Prerequisites

- **Maven**: Ensure Maven is installed and configured on your system.
- **GitHub Personal Access Token (PAT)**: You’ll need a PAT with `read:packages` permission to access the library.

### 1. Configure Maven to Authenticate with GitHub Packages

You’ll need to configure Maven to authenticate with GitHub Packages using your PAT.

1. Locate your Maven `settings.xml` file:
    - On **Windows**: `C:\Users\<YourUsername>\.m2\settings.xml`
    - On **Linux/macOS**: `~/.m2/settings.xml`

   If the file doesn’t exist, create a new one.

2. Add the following to your `settings.xml`:

   ```xml
   <settings>
       <servers>
           <server>
               <id>github</id>
               <username>YOUR_GITHUB_USERNAME</username>
               <password>${env.GITHUB_TOKEN}</password> <!-- Or replace with your PAT directly -->
           </server>
       </servers>
   </settings>
   ```

    - Replace `YOUR_GITHUB_USERNAME` with your GitHub username.
    - Set the `GITHUB_TOKEN` environment variable to your PAT. Alternatively, you can place the PAT directly in the `<password>` field.

### 2. Add GitHub Packages as a Repository

In your new project’s `pom.xml`, add the following repository entry so Maven knows where to find the library:

```xml
<repositories>
    <repository>
        <id>github</id>
        <name>GitHub AlekOmOm Packages</name>
        <url>https://maven.pkg.github.com/AlekOmOm/library</url>
    </repository>
</repositories>
```

### 3. Add the Library Modules as Dependencies

To use the library in your project, add the specific modules you need as dependencies. For example:

```xml
<dependencies>
    <dependency>
        <groupId>com.Alek0m0m</groupId>
        <artifactId>library-spring</artifactId>
        <version>2.1.3</version> <!-- replace with latest version or release -->
    </dependency>
    <dependency>
        <groupId>com.Alek0m0m</groupId>
        <artifactId>library-jpa</artifactId>
        <version>2.1.3</version>
    </dependency>
    <dependency>
        <groupId>com.Alek0m0m</groupId>
        <artifactId>library-core</artifactId>
        <version>2.1.3</version>
    </dependency>
</dependencies>
```

> **Note**: You only need to add the specific submodules (`library-spring`, `library-jpa`, `library-core`) instead of the parent module.

### 4. Run Maven Install

With your `pom.xml` configured, run the following command to download dependencies and set up your project:

```bash
mvn clean install
```

### 5. Use the Library in Your Code

You can now use the classes and methods provided by the library in your project. For example:

```java
import com.Alek0m0m.library.jpa.BaseEntity;

public class User extends BaseEntity {
    // Your custom code here
}
```

Or, if you are using the `BaseRESTController`:

```java
import com.Alek0m0m.library.spring.web.mvc.BaseRESTController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends BaseRESTController<User, UserDTO, UserService> {

   @Autowired
   public MyController(UserService service) {
      super(service);
   }

   // MyController now has implemented CRUD methods
   public void userCustomMethod() {
       service.userCustomServiceMethod();
       service.findAll(); // findAll() is inherited from BaseService
   }
}
```

Now you can utilize functionalities provided by the library modules.

---

## Troubleshooting

- **Error fetching dependency**: Ensure the PAT in your `settings.xml` is valid and has `read:packages` permission.
- **Snapshot versions**: If you’re using a snapshot version (e.g., `1.0-SNAPSHOT`), ensure that `<snapshotRepository>` is configured in `pom.xml` if needed.

---

Let me know if you'd like to add more details or make any further adjustments!