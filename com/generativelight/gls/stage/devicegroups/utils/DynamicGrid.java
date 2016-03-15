package com.generativelight.gls.stage.devicegroups.utils;

import processing.data.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A DynamicGrid stores objects in a row and col based map.
 * Rows can have different lengths.
 *
 *
 * Example:
 *
 * | 0,0 |
 * | 1,0 | 1,1 |
 * | 2,0 | 2,1 | 2,2 |
 *
 * Created: Generative Light, Janneck Wullschleger, 2016
 */
public class DynamicGrid {

    private ArrayList<ArrayList<Object>> grid;
    private int width = 0;

    /**
     * Default constructor
     */
    public DynamicGrid() {
        grid = new ArrayList<>();
    }

    /**
     * Constructor for creating grid from a json config file.
     * @param json the JSONArray which represents the rows and cols
     * @param deviceMap a hashmap which contains the device objects whith their ids from the config file
     */
    public DynamicGrid(JSONArray json, HashMap deviceMap) {
        this();
        for (int row = 0; row < json.size(); row++) {
            addRow();
            JSONArray rowJson = json.getJSONArray(row);
            for (int col = 0; col < rowJson.size(); col++) {
                addCol(row);
                set(deviceMap.get(rowJson.getInt(col)), row, col);
            }
        }

    }

    /**
     * Returns the count of rows in the grid.
     * @return the height
     */
    public int getHeight() { return grid.size(); }

    /**
     * Returns the count of cols in the longest row.
     * @return the width
     */
    public int getWidth() { return width; }

    /**
     * Returns the count of cls in the given row.
     * @param row the row of which the col count is queried
     * @return the count of cols
     */
    public int getWidthOfRow(int row) {
        if (row < getHeight()) {
            return grid.get(row).size();
        } else {
            return 0;
        }
    }

    /**
     * Adds a row on the end of the grid.
     */
    public void addRow() {
        grid.add(new ArrayList<>());
    }

    /**
     * Removes a row from the grid
     * @param row teh row which should be removed.
     */
    public void removeRow(int row) {
        if (checkIfCellExists(row, 0)) {
            grid.remove(row);
        }
    }

    /**
     * Adds a col at the end of the given row.
     * @param row the row where the new col should be added
     */
    public void addCol(int row) {
        if (row < getHeight()) {
            grid.get(row).add(null);
        }
        setWidth();
    }

    /**
     * Adds a col in at the given position in the given row.
     * @param row the row where the new col should be added
     * @param col the position where the new col should be added. All cols right to this position are shifted one to the right
     */
    public void addCol(int row, int col) {
        if ((row < getHeight()) && (col >= 0)) {
            if (col > getWidthOfRow(row)) {
                grid.get(row).add(null);
            } else {
                grid.get(row).add(col, null);
            }
        }
        setWidth();
    }

    /**
     * Removes a given col from the given row.
     * @param row the row where the col should be removed
     * @param col the col which should be removed
     */
    public void removeCol(int row, int col) {
        if (checkIfCellExists(row, col)) {
            grid.get(row).remove(col);
        }
        setWidth();
    }

    /**
     * Sets a reference to an object at the given row/col position in the grid.
     * @param object the object
     * @param row the row
     * @param col the col
     */
    public void set(Object object, int row, int col) {
        if (checkIfCellExists(row, col)) {
            grid.get(row).set(col, object);
        }
    }

    /**
     * Returns the object at a given row/col position in the grid.
     * @param row the row
     * @param col the col
     * @return the object if one is set at the given position or null otherwise
     */
    public Object get(int row, int col) {
        if (checkIfCellExists(row, col)) {
            return grid.get(row).get(col);
        } else {
            return null;
        }
    }

    /**
     * Scans the grid for the longest row and sets its length to the field width.
     */
    private void setWidth() {
        for (int row = 0; row < getHeight(); row++) {
            if (grid.get(row).size() > width) {
                width = grid.get(row).size();
            }
        }
    }

    /**
     * Checks if a given row/col position exists in the grid.
     * @param row the row
     * @param col the col
     * @return true if the cell exists or false otherwise
     */
    protected boolean checkIfCellExists(int row, int col) {
        return (row < getHeight()) && (col < grid.get(row).size());
    }

}
