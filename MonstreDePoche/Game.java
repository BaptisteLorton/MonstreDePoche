package MonstreDePoche;

import MonstreDePoche.interfaces.Interface;

public class Game {
    public static void main(String[] args) {
        Interface gameInterface = new Interface();
        gameInterface.start();
    }
}

// Get-ChildItem -Recurse -Filter *.java MONSTREDEPOCHE | ForEach-Object { $_.FullName } > sources.txt
// javac -d bin (Get-Content sources.txt)
// java -cp bin MonstreDePoche.Game