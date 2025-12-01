/*
Problem Description:
Create families of UI components for multiple platforms without changing client code.
*/

interface Button {
    void paint();
}

interface Checkbox {
    void toggle();
}

interface UIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

class MacButton implements Button {
    @Override
    public void paint() {
        System.out.println("Rendering Mac Button");
    }
}

class MacCheckbox implements Checkbox {
    @Override
    public void toggle() {
        System.out.println("Toggling Mac Checkbox");
    }
}

class WinButton implements Button {
    @Override
    public void paint() {
        System.out.println("Rendering Windows Button");
    }
}

class WinCheckbox implements Checkbox {
    @Override
    public void toggle() {
        System.out.println("Toggling Windows Checkbox");
    }
}

class MacUIFactory implements UIFactory {
    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}

class WinUIFactory implements UIFactory {
    @Override
    public Button createButton() {
        return new WinButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WinCheckbox();
    }
}

class FactoryProducer {

    public static UIFactory getFactory() {

        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("mac")) {
            return new MacUIFactory();
        } else {
            return new WinUIFactory();
        }
    }
}

public class AbstractFactoryPattern {
    public static void main(String[] args) {

        UIFactory factory = FactoryProducer.getFactory();

        Button button = factory.createButton();
        Checkbox checkbox = factory.createCheckbox();

        button.paint();
        checkbox.toggle();
    }
}
