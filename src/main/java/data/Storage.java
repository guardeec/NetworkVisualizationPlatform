package data;

import data.graphicalmodelsdata.ParamsGlyph;
import data.graphicalmodelsdata.ParamsGraph;
import data.graphicalmodelsdata.ParamsMatrix;
import data.graphicalmodelsdata.ParamsTreeMap;
import data.networkdata.Network;
import data.rawdata.csvdata.CsvData;
import data.rawdata.csvdata.CsvPattern;
import data.rawdata.maxpatroldata.MaxPatrolData;
import parsers.csv.CsvParser;
import parsers.graphicalmodels.GlyphParser;
import parsers.graphicalmodels.GraphParser;
import parsers.graphicalmodels.MatrixParser;
import parsers.graphicalmodels.TreeMapParser;
import parsers.innerstructure.NetworkParser;
import parsers.innerstructure.NetworkToXMLParser;
import parsers.maxpatrol.MaxpatrolParser;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Guardeec on 27.04.16.
 */
/*
Временное храналище
 */
public class Storage implements RawDataImpl, GraphicalDataImpl {

    //parsers and csv pattern
    private MaxpatrolParser maxPatrolParser;
    private CsvParser csvParser;
    private List<CsvPattern> patternsList = new LinkedList<>();
    private NetworkParser networkParser;
    private GraphParser graphParser;
    private GlyphParser glyphParser;
    private MatrixParser matrixParser;
    private TreeMapParser treeMapParser;

    //raw data
    private File csvFile;
    private File maxPatrolFile;
    private CsvData csvData;
    private MaxPatrolData maxPatrolData;

    //inner structure
    private Network network;

    //graphical structures
    private ParamsGraph paramsGraph;
    private ParamsGlyph paramsGlyph;
    private ParamsMatrix paramsMatrix;
    private ParamsTreeMap paramsTreeMap;

    public Storage(
            MaxpatrolParser maxPatrolParser,
            CsvParser csvParser,
            NetworkParser networkParser,
            GraphParser graphParser,
            GlyphParser glyphParser,
            MatrixParser matrixParser,
            TreeMapParser treeMapParser
    ){
        this.maxPatrolParser = maxPatrolParser;
        this.csvParser = csvParser;
        this.networkParser = networkParser;
        this.graphParser = graphParser;
        this.glyphParser = glyphParser;
        this.matrixParser = matrixParser;
        this.treeMapParser = treeMapParser;
        System.out.println("Storage is ready!");
    }



    @Override
    public CsvData getCsv() {
        return this.csvData;
    }

    @Override
    public MaxPatrolData getMaxPatrolLog() {
        return this.maxPatrolData;
    }

    @Override
    public boolean setCsv (File csv, String patternName) {
        //csvdata может быть загружен только после maxPatrolLog
        //возвращаем ошибку если maxPatrolLog не загружен
        if (maxPatrolData==null){
            return false;
        }
        //ищем используемый паттерн
        //возвращаем ошибку если паттерн не найден
        CsvPattern usedPattern = null;
        for (CsvPattern pattern : this.patternsList){
            if (pattern.getPatternName().compareTo(patternName)==0){
                usedPattern = pattern;
            }
        }
        if (usedPattern==null){
            return false;
        }
        //парсим csvdata
        //если ошибка парсинга - возвращаем ошибку
        try {
            this.csvData = csvParser.getCsvData(csv, usedPattern);
            this.csvFile = csv;
            this.network = networkParser.parseToInnerStructure(this.maxPatrolData, this.csvData, "test", "Based on MaxPatrolLog: "+this.maxPatrolFile.getName()+"; and on: "+this.csvFile.getName());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        //преобразуем в графические модели
        this.paramsGraph = graphParser.parseToGraph(this.network);
        this.paramsGlyph = glyphParser.parseToGlyph(this.network);
        this.paramsMatrix = matrixParser.parseToMatrix(this.network);
        this.paramsTreeMap = treeMapParser.parseToMap(this.network);

        NetworkToXMLParser networkToXMLParser = new NetworkToXMLParser();

        System.out.println("!!!");
        System.out.println(networkToXMLParser.parse(this.network));

        return true;
    }

    @Override
    public boolean setMaxPatrolLog(File maxPatrolLog) {
        //обновляем csvdata
        this.csvData = null;
        //парсим MaxPatrolLog
        //выводим ошибку если ошибка парсинга
        try {
            this.maxPatrolData = this.maxPatrolParser.getMaxPatrolData(maxPatrolLog);
        } catch (IOException e) {
            e.printStackTrace();
            this.maxPatrolData = null;
            return false;
        }
        this.maxPatrolFile = maxPatrolLog;
        System.out.println(this.maxPatrolData.getDataAsString());
        return true;
    }

    @Override
    public boolean setPattern(CsvPattern csvPattern) {
        for (CsvPattern existingPattern : this.patternsList){
            if (existingPattern.getPatternName().compareTo(csvPattern.getPatternName())==0){
                return false;
            }
        }
        this.patternsList.add(csvPattern);
        return true;
    }

    @Override
    public boolean removeCsvPattern(String patternName) {
        Iterator<CsvPattern> iterator = this.patternsList.iterator();
        while (iterator.hasNext()){
            CsvPattern csvPattern = iterator.next();
            if (csvPattern.getPatternName().compareTo(patternName)==0){
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public List<CsvPattern> getCsvPatternList() {
        return this.patternsList;
    }

    @Override
    public ParamsGraph getGraph() {
        return this.paramsGraph;
    }

    @Override
    public ParamsGlyph getGlyph() {
        return this.paramsGlyph;
    }

    @Override
    public ParamsMatrix getMatrix() {
        return this.paramsMatrix;
    }

    @Override
    public ParamsTreeMap getTreeMap() {
        return this.paramsTreeMap;
    }
}
