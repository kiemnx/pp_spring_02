package vn.plusplus.spring.designpattern.adapter;

public class AdapterMain {
    public static void main(String[] args) {
        CarInterface bugatti = new Bugatti();
        XeOtoAdapter adapter = new XeOtoAdapter(bugatti);

        double speed = adapter.getSpeed();
        System.out.println(speed);
    }
}
