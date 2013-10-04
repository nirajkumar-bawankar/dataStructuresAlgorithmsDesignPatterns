Strategy DesignPatterns
=======================
Use this design pattern when you need your class to use one of several 
behaviors dynamically.

In other words, you want your class to be able to choose from: 
-doingABehavior
-notDoingABehavior
-DoingABehaviorDifferently
...

-----------------------------------Pros----------------------------------------
1. reduces long lists of conditional statements
2. avoids duplicate code
3. prevents a super class change to change it's sub classes
4. abstracts possibly complicated code from user

-----------------------------------Cons----------------------------------------
1. increase number of classes