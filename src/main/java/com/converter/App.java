package com.converter;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import org.omg.CORBA.Object;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     * @throws InterruptedException
     * @throws IOException
     * @throws JsonProcessingException
     */
    public static void main(String[] args) throws InterruptedException, JsonProcessingException, IOException {

        // WebDriverManager.chromedriver().setup();
        // WebDriver driver = new ChromeDriver();
        // driver.get("https://google.com");
        // Thread.sleep(3000);
        // driver.quit();

        File input = new File("src/data/snakes.csv");
        //removeWhitespaces(input);

        File output = new File("output/output.json");
        CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
        CsvMapper csvMapper = new CsvMapper();
 
        // Read data from CSV file
        List<java.lang.Object> readAll = csvMapper.readerFor(Map.class).with(csvSchema).readValues(input).readAll();
 
        ObjectMapper mapper = new ObjectMapper();
 
        // Write JSON formated data to output.json file
        mapper.writerWithDefaultPrettyPrinter().writeValue(output, readAll);
 
        // Write JSON formated data to stdout
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(readAll));

    }

    // public static void removeWhitespaces(File file) {
    //     try(BufferedReader br = new BufferedReader(new FileReader(file)); 
    //         FileWriter fw = new FileWriter("empty_file.csv")) {
    //         String st;
    //         while((st = br.readLine()) != null){
    //             fw.write(st.replaceAll("\\s+", " ").trim().concat("\n"));
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
}


