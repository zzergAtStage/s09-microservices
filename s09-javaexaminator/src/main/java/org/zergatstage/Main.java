package org.zergatstage;

/**
 * @author father
 */
class Base {
    final public void show() {
        System.out.println("Base::show() called");
    }
}

class Derived extends Base {
//    public void show() {
//        System.out.println("Derived::show() called");
//    }
}

class Main {
    public static void main(String[] args) {
        Base b = new Derived();;
        b.show();
    }
}
