package com.BDConsoleApp.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class QueryReader {
    private Map<String, String> queries;

    public QueryReader(){
        queries = new HashMap<>();
        InputStream inputStream = QueryReader.class.getResourceAsStream("queries.sql");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String line;
            boolean queryStart = true;
            String queryName = "";
            String queryStatement = "";
            while (true){
                line = bufferedReader.readLine();
                if (line == null)
                    break;
                if (line.length() == 0)
                    continue;

                if (queryStart){
                    queryName = line.substring(0, line.indexOf("{"));
                    queryStart = false;
                    continue;
                }

                if (line.equals("}")){
                    queryStatement = queryStatement.trim().replaceAll("\\s+", " ");
                    queries.put(queryName, queryStatement);
//                    System.out.printf("[%s][%s]\n", queryName, queryStatement);
                    queryStart = true;
                    queryStatement = "";
                    continue;
                }

                queryStatement += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getQueries() {
        return queries;
    }
}
