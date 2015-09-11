# BubbleTrouble

This is an improvement of the famous BubbleTrouble game. BubbleTrouble is an intensive hardcore 2D game, featuring several groundbraking game mechanics. These features include, but are not limited to, realistic simulation of gravity, invisible walls, gigantic bouncing balls and smooth animations. 

## Getting started

### How to compile

To compile the game, the easiest way is to use the pom.xml with maven. It is recommended to use Oracle JDK 8 or Oracle JDK 7. Using OpenJDK to compile will fail, since the JDK doesn't include `javax.jnlp`. To still use OpenJDK, include `javaws.jar` in the build path. 

### Running the game

To run the game, you will need to include `libs/` to the `java.library.path`. This can be done in Eclipse or IntelliJ IDEA by editing the run configuration. Add the following arguments to VM options: 
``` 
-Djava.library.path=libs/
```

### Keybindings

- **Left arrow**: Move player to the left
- **Right arrow**: Move player to the right
- **Space**: Shoot bubble popper

