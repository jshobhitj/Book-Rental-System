package com.impetus.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;



// TODO: Auto-generated Javadoc
/**
 * The Class CSVReadService.
 */
@Service
public class CSVReadService {
    
    /** The Constant LOGGER. */
     static final Logger LOGGER = Logger.getLogger(CSVReadService.class);
     private static final String STRING1="C:/Users/shobhit.jain/Desktop/book.csv";
    // add/update/delete books
    /**
     * Convert csv to java.
     *
     * @return the list
     */
    public List<String> convertCsvToJava() {
        List<String> bookList = new ArrayList<String>();
        try {
            String csvFileToRead = STRING1;
            BufferedReader br = null;
            String line = "";
            String splitBy = ",";
            
            try {

                br = new BufferedReader(new FileReader(csvFileToRead));
                while ((line = br.readLine()) != null) {

                    // split on comma(',')
                    String[] books = line.split(splitBy);
                    String stringBook = "";
                    for(int i=0;i<12;i++){
                      if (i<11){
                      stringBook=stringBook.concat(books[i]+","); 
                      }else{
                       stringBook=stringBook.concat(books[i]);
                      }
                     }
                    bookList.add(stringBook);
                }
            } catch (FileNotFoundException e) {
                LOGGER.info(e);

            } catch (IOException e) {
                LOGGER.info(e);
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        LOGGER.info(e);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.info(e);
        }
            return bookList;

    }

}