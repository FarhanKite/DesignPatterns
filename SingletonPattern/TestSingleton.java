/*
Problem Description:
Ensure a class creates only one instance and provides a global 
access point to avoid inconsistent state or resource conflicts.
*/

class Singleton {

    // private constructor: no external creation allowed
    private Singleton() {
        System.out.println("Singleton created");
    }

    // Inner static class: instance created only when needed
    private static class Holder {
        private static final Singleton INSTANCE = new Singleton();
    }

    // Global access method
    public static Singleton getInstance() {
        return Holder.INSTANCE;
    }
}

public class TestSingleton {
    public static void main(String[] args) {

        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        System.out.println(s1 == s2); // true
    }
}
