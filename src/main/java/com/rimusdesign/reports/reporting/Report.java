package com.rimusdesign.reports.reporting;


import java.io.IOException;


/**
 * An interface that defines minimum API requirements for
 * report generator to be used by {@Link ReportManager}.
 *
 * @author Rimas Krivickas.
 */
public interface Report {


    void generate (ReportManager reportManager) throws IOException;

}
