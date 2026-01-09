# POCKET MONSTER

## HOW TO COMPILE 

In TP repository :

```bash
find MonstreDePoche -name "*.java" > sources.txt
javac -d bin $(cat sources.txt)
java -cp bin MonstreDePoche.Game```

## HOW TO PLAY

After launching the game, the first things to do is to choose the name of the two players.

Then, you can choose 3 monsters, to have an information about a monster just enter its number. To choose your final team, you just need to type the 3 monster's number separated by spaces.

You can then select 5 items with the same logic than the monsters.

During the battle, you just need to enter the number of the action you want to do :

- Attack
- Use an object
- Switch monster
- Surrender

To select an attack, an object or a monster, just enter its number.

## WHAT HE ADDED

- The first things to do is to turn on your speakers because there is music ! One when you're choosing your monsters and one for the battle.
- Like we explained just before, you also have the possibility to choose your monsters and objects.
- We brought details to our interface, even if it's in the console.
- He added some objects to cover all the statistics of a monster.
