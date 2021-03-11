# SC_MDE

## Repository Layout

1) The **Mde** directory contains the Xatkit bot for code generation (Yet to complete it!!).

2) The **scdsl** directory contains the Xtext project that defines the DSL.

3) The **src** directory contains some example DSL files.

4) The **src-gen** directory contains the code generated from the example DSL files.

## How to run the platform

1) Clone this repository.

2) Install Xatkit.

3) Copy the **Mde** directory in the cloned repository to `xatkit/xatkit-examples`.

4) Again open the terminal and then, type in these commands

```
mvn clean compile
mvn exec:java -Dexec.mainClass="com.xatkit.example.MdeBot"
```

5) Then, open a Web-browser and go to link - http://localhost:5000/admin
