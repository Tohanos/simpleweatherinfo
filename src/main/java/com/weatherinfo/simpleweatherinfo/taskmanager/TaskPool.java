package com.weatherinfo.simpleweatherinfo.taskmanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskPool {

    private List<String> locations = new ArrayList<>();
    private final Logger logger = LoggerFactory.getLogger(TaskPool.class);
    private static TaskPool INSTANCE;
    private AtomicInteger currentTaskNumber;

    private String defaultLocation;

    private TaskPool() {
        currentTaskNumber = new AtomicInteger(0);
    }

    public static TaskPool getInstance() {
        if (INSTANCE == null) INSTANCE = new TaskPool();
        return INSTANCE;
    }

    public void setDefaultLocation(String location) {
        defaultLocation = location;
    }

    public void add(String newLocation) {
        if (!locations.contains(newLocation))
            locations.add(newLocation);
    }

    public void remove(String oldLocation) {
        locations.remove(oldLocation);
    }

    public String getNext() {
        if (locations.size() == 0) {
            return defaultLocation;
        }
        if (currentTaskNumber.get() < locations.size()) {
            String location = locations.get(currentTaskNumber.get());
            logger.info("Get from queue " + location);
            if (currentTaskNumber.incrementAndGet() > locations.size() - 1)
                currentTaskNumber.set(0);
            return location;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public boolean hasNext() {
        return currentTaskNumber.get() < locations.size() - 1;
    }

    public List<String> getLocations() {
        return locations;
    }

    public Integer getSize() {
        return locations.size();
    }

}
