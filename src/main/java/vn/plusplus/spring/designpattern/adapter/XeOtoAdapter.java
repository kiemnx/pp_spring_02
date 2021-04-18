package vn.plusplus.spring.designpattern.adapter;

public class XeOtoAdapter implements CarInterface{
    CarInterface carInterface;

    public XeOtoAdapter(CarInterface carInterface) {
        this.carInterface = carInterface;
    }

    @Override
    public double getSpeed() {
        return carInterface.getSpeed() * 1.60934;
    }
}
