# SC_MDE

## Repository Layout

1) The **Mde** directory contains the Xatkit bot for code generation.

2) The **scdsl** directory contains the Xtext project that defines the DSL.

3) The **src** directory contains some example DSL files.

4) The **src-gen** directory contains the code generated from the example DSL files.

## How to run the platform

### Method - 1 (All Users)

1) Install Java 8 and make it the default java compiler using the commands:

```
sudo apt update
sudo apt install openjdk-8-jdk openjdk-8-jre
sudo update-java-alternatives --jre-headless --jre --set java-1.8.0-openjdk-amd64
```

2) Clone the repository using the command `git clone git@github.com:smishy05/chatbotmde.git`

3) Go inside the `MdeBot` directory and run the jar file using `java -jar run.jar`

4) Then, open a Web-browser and go to link - http://localhost:5000/admin

### Method - 2 (Linux Users) 

1) Download the folder from the line - https://drive.google.com/drive/folders/1siM4dV1ZbragX3FPTZhWUSsk2UAwps6n?usp=sharing .

2) Open the terminal where you have the `xatkit-installation` folder and type these commands:

```
sudo chmod +x install.sh
sudo ./install.sh
```
Wait for the installation to end

3) After the installation ends, type these commands in the terminal:

```
sudo chmod +x run.sh
sudo ./run.sh
```

4) Then, open a Web-browser and go to link - http://localhost:5000/admin
