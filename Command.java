package labs.command_pattern;

interface Command {
    void execute();
    void undo();
}

class NoCommand implements Command {
    public void execute() {}
    public void undo() {}
}

class Light {
    String name;
    public Light(String name) { this.name = name; }
    public void on() { System.out.println(name + " light is ON"); }
    public void off() { System.out.println(name + " light is OFF"); }
}

class TV {
    String name;
    int volume = 10;
    public TV(String name) { this.name = name; }
    public void on() { System.out.println(name + " TV is ON"); }
    public void off() { System.out.println(name + " TV is OFF"); }
    public void volumeUp() { volume++; System.out.println(name + " TV volume: " + volume); }
    public void volumeDown() { volume--; System.out.println(name + " TV volume: " + volume); }
    public int getVolume() { return volume; }
    public void setVolume(int v) { volume = v; System.out.println(name + " TV volume set to: " + volume); }
}

class Fan {
    String name;
    enum Speed { OFF, LOW, MEDIUM, HIGH }
    Speed speed = Speed.OFF;
    public Fan(String name) { this.name = name; }
    public void cycle() {
        switch (speed) {
            case OFF -> speed = Speed.LOW;
            case LOW -> speed = Speed.MEDIUM;
            case MEDIUM -> speed = Speed.HIGH;
            case HIGH -> speed = Speed.LOW;
        }
        System.out.println(name + " fan speed: " + speed);
    }
    public void off() { speed = Speed.OFF; System.out.println(name + " fan is OFF"); }
    public Speed getSpeed() { return speed; }
    public void setSpeed(Speed s) { speed = s; System.out.println(name + " fan speed set to: " + speed); }
}

class LightOnCommand implements Command {
    Light light;
    public LightOnCommand(Light light) { this.light = light; }
    public void execute() { light.on(); }
    public void undo() { light.off(); }
}

class LightOffCommand implements Command {
    Light light;
    public LightOffCommand(Light light) { this.light = light; }
    public void execute() { light.off(); }
    public void undo() { light.on(); }
}

class TVOnCommand implements Command {
    TV tv;
    public TVOnCommand(TV tv) { this.tv = tv; }
    public void execute() { tv.on(); }
    public void undo() { tv.off(); }
}

class TVOffCommand implements Command {
    TV tv;
    public TVOffCommand(TV tv) { this.tv = tv; }
    public void execute() { tv.off(); }
    public void undo() { tv.on(); }
}

class TVVolumeUpCommand implements Command {
    TV tv;
    int previous;
    public TVVolumeUpCommand(TV tv) { this.tv = tv; }
    public void execute() { previous = tv.getVolume(); tv.volumeUp(); }
    public void undo() { tv.setVolume(previous); }
}

class TVVolumeDownCommand implements Command {
    TV tv;
    int previous;
    public TVVolumeDownCommand(TV tv) { this.tv = tv; }
    public void execute() { previous = tv.getVolume(); tv.volumeDown(); }
    public void undo() { tv.setVolume(previous); }
}

class FanOnCommand implements Command {
    Fan fan;
    Fan.Speed previous;
    public FanOnCommand(Fan fan) { this.fan = fan; }
    public void execute() { previous = fan.getSpeed(); fan.cycle(); }
    public void undo() { fan.setSpeed(previous); }
}

class FanOffCommand implements Command {
    Fan fan;
    Fan.Speed previous;
    public FanOffCommand(Fan fan) { this.fan = fan; }
    public void execute() { previous = fan.getSpeed(); fan.off(); }
    public void undo() { fan.setSpeed(previous); }
}

class RemoteControl {
    Command[] onCommands;
    Command[] offCommands;
    Command lastCommand = new NoCommand();
    boolean lastWasUndone = false;

    public RemoteControl() {
        onCommands = new Command[7];
        offCommands = new Command[7];
        Command no = new NoCommand();
        for (int i = 0; i < 7; i++) {
            onCommands[i] = no;
            offCommands[i] = no;
        }
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        if (slot < 0 || slot >= onCommands.length) return;
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void pushOnButton(int slot) {
        if (slot < 0 || slot >= onCommands.length) return;
        onCommands[slot].execute();
        lastCommand = onCommands[slot];
        lastWasUndone = false;
    }

    public void pushOffButton(int slot) {
        if (slot < 0 || slot >= offCommands.length) return;
        offCommands[slot].execute();
        lastCommand = offCommands[slot];
        lastWasUndone = false;
    }

    public void pushUndoButton() {
        if (lastCommand == null) return;
        if (!lastWasUndone) {
            lastCommand.undo();
            lastWasUndone = true;
        } else {
            lastCommand.execute();
            lastWasUndone = false;
        }
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("\n------- Remote Control -------\n");
        for (int i = 0; i < onCommands.length; ++i) {
            sb.append("[slot " + i + "] " + onCommands[i].getClass().getName() + "    " + offCommands[i].getClass().getName() + "\n");
        }
        return sb.toString();
    }
}

public class Client {
    public static void main(String[] args) {
        Light livingRoomLight = new Light("Living Room");
        Light kitchenLight = new Light("Kitchen");
        TV livingRoomTV = new TV("Living Room");
        Fan ceilingFan = new Fan("Ceiling");

        RemoteControl remote = new RemoteControl();

        LightOnCommand livingOn = new LightOnCommand(livingRoomLight);
        LightOffCommand livingOff = new LightOffCommand(livingRoomLight);
        LightOnCommand kitchenOn = new LightOnCommand(kitchenLight);
        LightOffCommand kitchenOff = new LightOffCommand(kitchenLight);

        TVOnCommand tvOn = new TVOnCommand(livingRoomTV);
        TVOffCommand tvOff = new TVOffCommand(livingRoomTV);
        TVVolumeUpCommand tvVolUp = new TVVolumeUpCommand(livingRoomTV);
        TVVolumeDownCommand tvVolDown = new TVVolumeDownCommand(livingRoomTV);

        FanOnCommand fanOn = new FanOnCommand(ceilingFan);
        FanOffCommand fanOff = new FanOffCommand(ceilingFan);

        remote.setCommand(0, livingOn, livingOff);
        remote.setCommand(1, kitchenOn, kitchenOff);
        rem
