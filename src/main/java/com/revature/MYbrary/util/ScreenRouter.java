package com.revature.MYbrary.util;

import com.revature.MYbrary.screens.Screen;

public class ScreenRouter {
    private final LinkedList<Screen> screens;

    public ScreenRouter() {
        screens = new LinkedList<>();
    }

    public void addScreen(Screen screen) {
        screens.add(screen);
    }

    public void navigate(String route) throws Exception {
        System.out.println("~~~~ FLAG - ScreenRouter - L17 ~~~~\n screen size: " + screens.size());
        System.out.println("~~~~ FLAG - ScreenRouter - L18 ~~~~\nLooking for " + route);
        for (int i = 0; i < screens.size(); i++) {
            Screen thisScreen = screens.get(i);
            System.out.println("~~~~ FLAG - ScreenRouter - L20 ~~~~\n" + thisScreen.getName());
            if (thisScreen.getRoute().equals(route)) {
                thisScreen.render();
            }
        }
    }

}
