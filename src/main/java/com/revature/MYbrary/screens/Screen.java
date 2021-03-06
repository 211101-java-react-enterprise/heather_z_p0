package com.revature.MYbrary.screens;

import com.revature.MYbrary.util.ScreenRouter;

import java.io.BufferedReader;

public abstract class Screen {

    protected String name;
    protected String route;
    protected BufferedReader consoleReader;
    protected ScreenRouter router;

    public Screen(String name, String route, BufferedReader consoleReader, ScreenRouter router) {
        this.name = name;
        this.route = route;
        this.consoleReader = consoleReader;
        this.router = router;
    }

    public final String getName() {
        return name;
    }

    public final String getRoute() {
        return route;
    }

    public abstract void render() throws Exception;
}
