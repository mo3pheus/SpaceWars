package bootstrap;

import org.apache.log4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.artifacts.SpaceShip;
import util.SpaceShipFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

public class Driver {
    public static final String SEPARATOR =
            "==============================================================";

    public static Properties projectProperties = new Properties();
    public static Logger     logger            = LoggerFactory.getLogger(Driver.class);

    public static void main(String[] args) {
        try {
            String logFilePath = "";
            configureConsoleLogging(Boolean.parseBoolean(args[0]));
            logger.info(SEPARATOR);
            logger.info("Project properties are loaded. Log file generated for this run = " + logFilePath);
            projectProperties = getProjectProperties(args[1]);

            SpaceShip[] spaceShips = loadSpaceShips(args[1]);
            for (int i = 0; i < spaceShips.length; i++) {
                System.out.println(SEPARATOR);
                System.out.println(SpaceShipFactory.convertToString(spaceShips[i]));
            }

        } catch (IOException io) {
            logger.error("Error while reading the project properties file.", io);
        }
    }

    public static SpaceShip[] loadSpaceShips(String fileName) throws IOException {
        List<String> lines      = Files.readAllLines(Paths.get(fileName));
        SpaceShip[]  spaceShips = new SpaceShip[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            spaceShips[i] = SpaceShipFactory.generateSpaceShip(lines.get(i), ThreadLocalRandom.current().nextInt(1,30));
        }
        return spaceShips;
    }

    public static String configureLogging(boolean debug) {
        FileAppender fa = new FileAppender();

        if (!debug) {
            fa.setThreshold(Level.toLevel(Priority.INFO_INT));
            fa.setFile("executionLogs/log_infoLevel_report_" + Long.toString(System.currentTimeMillis()) + ".log");
        } else {
            fa.setThreshold(Level.toLevel(Priority.DEBUG_INT));
            fa.setFile("executionLogs/log_debugLevel_report_" + Long.toString(System.currentTimeMillis()) + ".log");
        }

        fa.setLayout(new EnhancedPatternLayout("%-6d [%25.35t] %-5p %40.80c - %m%n"));

        fa.activateOptions();
        org.apache.log4j.Logger.getRootLogger().addAppender(fa);
        return fa.getFile();
    }

    public static void configureConsoleLogging(boolean debug) {
        ConsoleAppender ca = new ConsoleAppender();
        if (!debug) {
            ca.setThreshold(Level.toLevel(Priority.INFO_INT));
        } else {
            ca.setThreshold(Level.toLevel(Priority.DEBUG_INT));
        }
        ca.setLayout(new EnhancedPatternLayout("%-6d [%25.35t] %-5p %40.80c - %m%n"));
        ca.activateOptions();
        org.apache.log4j.Logger.getRootLogger().addAppender(ca);
    }

    public static Properties getProjectProperties(String propertiesFilePath) throws IOException {
        logger.info("Properties file specified at location = " + propertiesFilePath);
        FileInputStream projFile   = new FileInputStream(propertiesFilePath);
        Properties      properties = new Properties();
        properties.load(projFile);
        return properties;
    }
}
