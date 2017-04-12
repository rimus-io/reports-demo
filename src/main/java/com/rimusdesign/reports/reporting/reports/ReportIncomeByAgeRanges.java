package com.rimusdesign.reports.reporting.reports;


import com.rimusdesign.reports.helpers.CSVCreator;
import com.rimusdesign.reports.helpers.Statistics;
import com.rimusdesign.reports.model.EmployeeAggregate;
import com.rimusdesign.reports.reporting.ReportBase;
import com.rimusdesign.reports.reporting.ReportManager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Report implementation that calculates average income per age range.
 *
 * @author Rimas Krivickas.
 */
public class ReportIncomeByAgeRanges extends ReportBase {


    /*
    TODO Clarify the requirement, as 'factor of 10' doesn't make sense when applied to age (25yo would become 250yo)
    For now we'll assume that the requirement is to set ranges in increments of 10 years
     */
    public ReportIncomeByAgeRanges (Path toFile) {

        super(toFile);
    }


    @Override
    public void generate (ReportManager reportManager) throws IOException {

        String[] header = new String[]{"Age Range", "Average Income"};
        List<String[]> content = new ArrayList<>();

        reportManager.getEmployeesByAgeRange().keySet().stream().forEach(key -> {

            // Generate range string
            String range = String.format("%.0f-%.0f", key.getStart() + 1, key.getEnd());

            // Calculate average income
            Set<EmployeeAggregate> employees = reportManager.getEmployeesByAgeRange().get(key);
            List<Double> collect = employees.stream().map(EmployeeAggregate::getSalary).collect(Collectors.toList());
            Double[] salaries = collect.toArray(new Double[collect.size()]);
            double income = Statistics.fractionRound(Stream.of(salaries).mapToDouble(Double::doubleValue).average().getAsDouble(), 2);

            // Append content
            content.add(new String[]{range, Double.toString(income)});
        });

        // Generate CSV
        CSVCreator.create(getToFile(), content, header);
    }

}
