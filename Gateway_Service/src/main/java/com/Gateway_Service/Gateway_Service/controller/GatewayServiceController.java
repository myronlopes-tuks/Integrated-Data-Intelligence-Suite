package com.Gateway_Service.Gateway_Service.controller;



import com.Gateway_Service.Gateway_Service.dataclass.*;


import com.Gateway_Service.Gateway_Service.service.AnalyseService;
import com.Gateway_Service.Gateway_Service.service.ImportService;
import com.Gateway_Service.Gateway_Service.service.ParseService;


//import com.netflix.discovery.DiscoveryClient;

import org.springframework.cloud.client.discovery.DiscoveryClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@RestController
@RequestMapping("/")
public class GatewayServiceController {

    @Autowired
    private ImportService importClient;

    @Autowired
    private ParseService parseClient;

    @Autowired
    private AnalyseService analyseClient;

    @Autowired
    private DiscoveryClient discoveryClient;



    /**
     * This method is used the map/convert the name os a service to its respective url on a specific host
     * @param serviceName This is string value of a service's name identity
     * @return String This is string value that would represent a url of a service
     */
    private String getServiceURL(String serviceName){
        return this.discoveryClient.getInstances(serviceName).get(0).getUri().toString();
    }

    /**
     * Test function, this methoe is used to test the service
     * @param key This is a path variable of string value
     * @return String This is a string value of a json test
     */
    @GetMapping(value ="/{key}", produces = "application/json")
    public String testNothing(@PathVariable String key) {
        String output = "";

        ImportTwitterRequest importRequest = new ImportTwitterRequest(key,10);
        ImportTwitterResponse importResponse = importClient.getTwitterDataJson(importRequest);

        if(importResponse.getFallback() == true)
            output = importResponse.getFallbackMessage();
        else
            output = importResponse.getJsonData();

        return output;
    }


    /**
     * This method is used to facilitate communication to all the Services.
     * Outputs data related to a topic/key.
     * @param key This is a path variable of string value
     * @return ResponseEntity<ArrayList<ArrayList<Graph>>>
     *     This object contains data representing a response from all the services combined.
     * @throws Exception This is thrown if exception caught in any of the Services.
     */
    @GetMapping(value = "/main/{key}", produces = "application/json")
    @CrossOrigin
    //@HystrixCommand(fallbackMethod = "fallback")
    public ResponseEntity<ArrayList<ArrayList<Graph>>> init(@PathVariable String key) throws Exception {
        ArrayList<ArrayList<Graph>> outputData = new ArrayList<>();

        //ArrayList <String> outputData = new ArrayList<>();
        HttpHeaders requestHeaders;

        /*********************IMPORT*************************/

        //String url = "http://Import-Service/Import/importData";
        //UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("value",key);

        ImportDataRequest importRequest = new ImportDataRequest(key,50);
        ImportDataResponse importResponse = importClient.importData(importRequest);

        if(importResponse.getFallback() == true) {
            //outputData.add(importResponse.getFallbackMessage());
            //return new ArrayList<>();//outputData;

            ErrorGraph errorGraph = new ErrorGraph();
            errorGraph.Error = importResponse.getFallbackMessage();

            ArrayList<Graph> data = new ArrayList<>();
            data.add(errorGraph);

            outputData.add( data);

            return new ResponseEntity<>(outputData,HttpStatus.OK);
        }

        System.out.println("***********************IMPORT HAS BEEN DONE*************************");



        /*********************PARSE*************************/

        ParseImportedDataRequest parseRequest = new ParseImportedDataRequest(DataSource.TWITTER, importResponse.getList().get(0).getData());//    DataSource.TWITTER,ImportResponse. getJsonData());
        ParseImportedDataResponse parseResponse = parseClient.parseImportedData(parseRequest);


        if(parseResponse.getFallback() == true) {
            //outputData.add(parseResponse.getFallbackMessage());
            //outputData.add();
            ErrorGraph errorGraph = new ErrorGraph();
            errorGraph.Error = parseResponse.getFallbackMessage();

            ArrayList<Graph> data = new ArrayList<>();
            data.add(errorGraph);

            outputData.add( data);

            return new ResponseEntity<>(outputData,HttpStatus.OK);
        }

        System.out.println("***********************PARSE HAS BEEN DONE*************************");



        /*********************ANALYSE*************************/

        AnalyseDataRequest analyseRequest = new AnalyseDataRequest(parseResponse.getDataList());//    DataSource.TWITTER,ImportResponse. getJsonData());
        AnalyseDataResponse analyseResponse = analyseClient.analyzeData(analyseRequest);


        if(analyseResponse.getFallback() == true) {
            ErrorGraph errorGraph = new ErrorGraph();
            errorGraph.Error = analyseResponse.getFallbackMessage();

            ArrayList<Graph> data = new ArrayList<>();
            data.add(errorGraph);

            outputData.add( data);

            return new ResponseEntity<>(outputData,HttpStatus.OK);
        }



        System.out.println("***********************ANALYSE HAS BEEN DONE*************************");


        /*********************VISUALISE**********************/

        /*************LINE**********/
        ArrayList<Graph> LineGraphArray = createlineGraph(analyseResponse.getPattenList());


        /*************NETWORK**********/
        ArrayList<Graph> NetworkGraphArray =  createNetworkGraph( analyseResponse.getPattenList());


        /************MAP**********/
        ArrayList<Graph> mapArray = createMapGraph();


        /************TIMELINE**********/
        ArrayList<Graph> TimelineArray = createTimelineGraph();

        outputData.add(LineGraphArray);
        outputData.add(NetworkGraphArray );
        //outputData.add(mapArray);
        outputData.add(TimelineArray);

        return new ResponseEntity<>(outputData,HttpStatus.OK);

    }




    public static class Graph{
        Graph(){}
    }

    public static class LineGraph extends Graph{
        public String name;
        public ArrayList<String> marker = new ArrayList<>();
        public ArrayList<String> data  = new ArrayList<>();
    }

    public static class NetworkGraph extends Graph{
        public String From;
        public String to;
    }

    public static class TimelineGraph extends Graph{
        public String x;
        public String name;
        public String label;
        public String description;

    }

    public static class mapGraph extends Graph{
        ArrayList<ArrayList> map = new ArrayList<>();

    }

    public static class ErrorGraph extends Graph{
        public String Error;
    }


    /**
     * This method is used to convert data into data representable as a line graph.
     * @param list This is an object that contains data that is going to be structured.
     * @return ArrayList<Graph> This object contains structured data for a line graph.
     */
    private ArrayList<Graph> createlineGraph(ArrayList<ArrayList> list){
        LineGraph vpos = new LineGraph();
        vpos.name = "Very Positive";
        vpos.marker.add("square");

        LineGraph pos = new LineGraph();
        pos.name = "Positive";
        pos.marker.add("square");

        LineGraph net = new LineGraph();
        net.name = "Neutral";
        net.marker.add("square");


        LineGraph neg = new LineGraph();
        neg.name = "Negative";
        neg.marker.add("square");


        LineGraph vneg = new LineGraph();
        vneg.name = "Very Negative";
        vneg.marker.add("square");




        ArrayList<ArrayList> rela = list;
        for(int i = 0; i < rela.size(); i++) {
            for (int j = 0;j< rela.get(i).size(); j++){
                if (rela.get(i).get(j).toString().equals("Very_Negative")){
                    int index = rela.get(i).size()-1;
                    vneg.data.add(rela.get(i).get(index).toString());
                }



                else if (rela.get(i).get(j).toString().equals("Negative")){
                    int index = rela.get(i).size()-1;
                    neg.data.add(rela.get(i).get(index).toString());
                }
                else if (rela.get(i).get(j).toString().equals("Neutral")){
                    int index = rela.get(i).size()-1;
                    net.data.add(rela.get(i).get(index).toString());
                }
                else if (rela.get(i).get(j).toString().equals("Positive")){
                    int index = rela.get(i).size()-1;
                    pos.data.add(rela.get(i).get(index).toString());
                }
                else if (rela.get(i).get(j).toString().equals("Very_Positive")){
                    int index = rela.get(i).size()-1;
                    vpos.data.add(rela.get(i).get(index).toString());
                }
            }

        }

        ArrayList<Graph> lineGraphArray = new ArrayList<>();
        lineGraphArray.add(vpos);
        lineGraphArray.add(pos);
        lineGraphArray.add(net);
        lineGraphArray.add(neg);
        lineGraphArray.add(vneg);

        return  lineGraphArray;
    }


    /**
     * This method is used to convert data into data representable as a network graph.
     * @param list This is an object that contains data that is going to be structured.
     * @return ArrayList<Graph> This object contains structured data for a network graph.
     */
    private ArrayList<Graph> createNetworkGraph(ArrayList<ArrayList> list){
        NetworkGraph temp;
        ArrayList<ArrayList> pdata = list;
        ArrayList<Graph> NetworkGraphArray = new ArrayList<>();





        for (int i = 0; i < pdata.size(); i++) {
            temp =  new NetworkGraph();
            temp.From = pdata.get(i).get(pdata.get(i).size()-3).toString();
            temp.to = "";
            for (int j = 0; j < pdata.get(i).size()-2; j++) {
                temp.to += pdata.get(i).get(j).toString() + ", ";
            }
            NetworkGraphArray.add(temp);
        }

        return NetworkGraphArray;
    }

    /**
     * This method is used to convert data into data representable as a map graph.
     * @return ArrayList<Graph> This object contains structured data for a map graph.
     */
    private ArrayList<Graph> createMapGraph(){
        ArrayList<Graph> mapArray = new ArrayList<>();
        ArrayList<String> coordinates;
        mapGraph mapG = new mapGraph();

        coordinates = new ArrayList<>();
        coordinates.add("za-ec");
        coordinates.add("100");
        mapG.map.add(coordinates);

        coordinates = new ArrayList<>();
        coordinates.add("za-np");
        coordinates.add("102");
        mapG.map.add(coordinates);

        coordinates = new ArrayList<>();
        coordinates.add("za-nl");
        coordinates.add("120");
        mapG.map.add(coordinates);

        coordinates = new ArrayList<>();
        coordinates.add("za-wc");
        coordinates.add("300");
        mapG.map.add(coordinates);

        coordinates = new ArrayList<>();
        coordinates.add("za-nc");
        coordinates.add("106");
        mapG.map.add(coordinates);

        coordinates = new ArrayList<>();
        coordinates.add("za-nw");
        coordinates.add("90");
        mapG.map.add(coordinates);

        coordinates = new ArrayList<>();
        coordinates.add("za-fs");
        coordinates.add("130");
        mapG.map.add(coordinates);

        coordinates = new ArrayList<>();
        coordinates.add("za-gt");
        coordinates.add("130");
        mapG.map.add(coordinates);

        coordinates = new ArrayList<>();
        coordinates.add("za-mp");
        coordinates.add("134");
        mapG.map.add(coordinates);

        mapArray.add(mapG);

        return mapArray;
    }

    /**
     * This method is used to convert data into data representable as a timeline graph.
     * @return ArrayList<Graph> This object contains structured data for a timeline graph.
     */
    private ArrayList<Graph> createTimelineGraph(){
        ArrayList<Graph> timelineArray = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            TimelineGraph timel = new TimelineGraph();
            timel.x = "2021,"+ Integer.toString(i) + ",11";
            timel.label = "MOCK";
            timel.name = "MOCK";
            timel.description = "MOCK";

            timelineArray.add(timel);
        }
        return timelineArray;
    }


}
