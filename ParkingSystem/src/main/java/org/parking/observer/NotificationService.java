package org.parking.observer;

public class NotificationService implements Observer {

    @Override
    public void update(String event) {
        System.out.println(event);
    }
}
