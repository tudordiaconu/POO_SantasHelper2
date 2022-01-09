package data;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ReadWrite {
    private Database database;
    private WriteDatabase writeDatabase;

    /** getter for the database used for writing */
    public WriteDatabase getWriteDatabase() {
        return writeDatabase;
    }

    /** setter for the database used for writing */
    public void setWriteDatabase(final WriteDatabase writeDatabase) {
        this.writeDatabase = writeDatabase;
    }

    public ReadWrite(final Database database, final WriteDatabase writeDatabase) {
        this.database = database;
        this.writeDatabase = writeDatabase;
    }


    /** method that reads from file into database */
    public void readAllData(final String file) throws IOException {
        database = new ObjectMapper().readerFor(Database.class).readValue(new File(file));
    }

    /** method that does the writing */
    public void writeAllData(final String file) throws IOException {
        ObjectMapper om = new ObjectMapper();
        om.writerWithDefaultPrettyPrinter().writeValue(new File(file), writeDatabase);
    }


    /** getter for the database used to store data */
    public Database getDatabase() {
        return database;
    }

    /** setter for the database used to store data */
    public void setDatabase(final Database database) {
        this.database = database;
    }
}
