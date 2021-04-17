public class Mobile extends Phone{
    @Override
    public void makeCall(String number) {
        System.out.println("Make call " + number);
    }

    @Override
    public void plugin(){
        System.out.println("Plugin");
    }

    @Override
    public void on() {
        System.out.println("On");
    }

    @Override
    public void off() {
        System.out.println("Off");
    }
}
