/*
Problem Description:
We need to create an object that has many optional or complex fields.
Using a single large constructor becomes hard to read and maintain, 
so we need a clean way to build the object step by step.
*/

class Laptop {

    private final String cpu;
    private final int ram;
    private final int storage;
    private final String gpu;
    private final String os;
    private final String screenType;

    private Laptop(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.gpu = builder.gpu;
        this.os = builder.os;
        this.screenType = builder.screenType;
    }

    static class Builder {
        private String cpu;
        private int ram;
        private int storage;
        private String gpu;
        private String os = "Windows"; // default value
        private String screenType;

        public Builder cpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder ram(int ram) {
            this.ram = ram;
            return this;
        }

        public Builder storage(int storage) {
            this.storage = storage;
            return this;
        }

        public Builder gpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public Builder os(String os) {
            this.os = os;
            return this;
        }

        public Builder screenType(String screenType) {
            this.screenType = screenType;
            return this;
        }

        public Laptop build() {
            return new Laptop(this);
        }
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "cpu='" + cpu + '\'' +
                ", ram=" + ram +
                ", storage=" + storage +
                ", gpu='" + gpu + '\'' +
                ", os='" + os + '\'' +
                ", screenType='" + screenType + '\'' +
                '}';
    }
}

public class BuilderPattern {
    public static void main(String[] args) {

        Laptop gamingLaptop = new Laptop.Builder()
                .cpu("Inter i9")
                .ram(32)
                .storage(1000)
                .gpu("RTX 4080")
                .os("Windows 11")
                .screenType("OLED")
                .build();

        Laptop officeLaptop = new Laptop.Builder()
                .cpu("Inter i5")
                .ram(8)
                .storage(256)
                .build();

        System.out.println(gamingLaptop);
        System.out.println(officeLaptop);
    }
}
