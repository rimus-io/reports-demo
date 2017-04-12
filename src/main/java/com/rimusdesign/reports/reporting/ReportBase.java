package com.rimusdesign.reports.reporting;


import java.io.IOException;
import java.nio.file.Path;


/**
 * Base implementation of {@Link Report} that
 * implements some commonly used methods.
 *
 * @author Rimas Krivickas.
 */
public abstract class ReportBase implements Report {


    private Path toFile;


    public ReportBase (Path toFile) {

        this.toFile = toFile;
    }


    protected Path getToFile () {

        return toFile;
    }


    @Override
    public abstract void generate (ReportManager reportManager) throws IOException;
}
