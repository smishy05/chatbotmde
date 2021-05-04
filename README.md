# SC_MDE

## Repository Layout

1) The **Mde** directory contains the Xatkit bot for code generation.

2) The **scdsl** directory contains the Xtext project that defines the DSL.

3) The **src** directory contains some example DSL files.

4) The **src-gen** directory contains the code generated from the example DSL files.

## How to run the platform

### Method - 1
1) Clone this repository.

2) Install Xatkit.

3) Copy the **Mde** directory in the cloned repository to `xatkit/xatkit-examples`.

4) Again open the terminal and then, type in these commands

```
mvn clean compile
mvn exec:java -Dexec.mainClass="com.xatkit.example.MdeBot"
```

5) Then, open a Web-browser and go to link - http://localhost:5000/admin

### Method - 2
This method is applicable for Linux users and Windows users using **Windows Subsystem for Linux(WSL)**.

1) Clone this repository.

2) Go into the `Chatbot Evaluation` directory.

3) Copy the `installation` folder to the destination where you would like to have the chatbot.

4) Open the terminal inside the `installation` folder and type these commands:

```
sudo chmod +x install.sh
sudo ./install.sh
```

Wait for the installation to end

5) After the installation ends, type these commands in the terminal:

```
sudo chmod +x run.sh
sudo ./run.sh
```

6) Then, open a Web-browser and go to link - http://localhost:5000/admin
