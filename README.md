# mousteroidsMVC
  Simple game of asteroids made as a training ground for MVC pattern (not in Spring MVC, just pure architectural idea), multi-threading, pixel perfect collision engine, TDD with jUnit and delta timing in java. Includes use of game objects linked with sprites, modular view, model and controller elements; with model written in pure java and view written in javafx. 
  
  Includes more designs patterns such as singletons and dependency injection (in pure java code); static Classes, abstract classes and interfaces, inheritance tree structures and tests. 
  All graphics are vector coded in view module, and may be replaced with other shapes or images (image sprite must have a method returning bounding polygon for model to calculate pixel-perfect collisions - after this initial information game logic doesn't need any data from view module).
  
  Application is running on javafx thread, and there is an additional custom thread invoking update events in given frequency, calculating any occurring lag, and passing delta value for smooth movement, using java concurrent-safe structures. 
  
  Includes custom 2d vector class "Double2D" used to calculate points in 2 dimensional space, which implements comparable interface, and includes equals, compareTo and hashCode methods, and can be added, subtracted or multiplied. (data structure mostly created for learning purposes, but found useful in project, especially in collision engine) 
  
  Collision engine and static classes are heavily (although not 100%) test covered in jUnit.
