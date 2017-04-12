package com.rimusdesign.reports.parsing.parsers;


import com.rimusdesign.reports.model.Department;
import com.rimusdesign.reports.parsing.CSVParser;


/**
 * Concrete implementation of {@Link CSVParser} for parsing {@Link Department} data.
 *
 * @author Rimas Krivickas.
 */
public class CSVParserDepartment implements CSVParser<Department> {


    @Override
    public Department parse (String line) throws IllegalArgumentException {

        // Validate
        if (line == null || line.trim().isEmpty())
            throw new IllegalArgumentException("Provided CSV line is invalid, make sure there are no missing commas etc.");

        String name;
        String[] lineItems = line.split(",");

        try {

            name = lineItems[0].trim();
        } catch (ArrayIndexOutOfBoundsException e) {

            throw new IllegalArgumentException("Provided CSV line is invalid, make sure there are no missing commas etc.");
        }

        return new Department(name);
    }
}
