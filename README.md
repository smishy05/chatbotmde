# SC_MDE

1) Clone this repository.

2) Install Xatkit.

3) After following all the steps for installation,open the terminal and type in these commands

```
cd $XATKIT_DEV
cd build
mkdir projects
```

4) Copy the directories **SC_MDE** and **SC_MDE_JSON** in the cloned repository to `$XATKIT_DEV/build/projects`.

5) Again open the terminal and then, type in these commands

```
cd $XATKIT_DEV/build/bin
chmod +x start-xatkit-linux.sh
./start-xatkit-linux.sh ../projects/SC_MDE/SC_MDE.properties
```

6) Then, open a Web-browser and go to link - http://localhost:5000/admin
