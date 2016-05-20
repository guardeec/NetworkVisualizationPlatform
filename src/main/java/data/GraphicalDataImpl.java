package data;

import data.graphicalmodelsdata.ParamsGlyph;
import data.graphicalmodelsdata.ParamsGraph;
import data.graphicalmodelsdata.ParamsMatrix;
import data.graphicalmodelsdata.ParamsTreeMap;

/**
 * Created by Guardeec on 27.04.16.
 */
public interface GraphicalDataImpl {
    public ParamsGraph getGraph();
    public ParamsGlyph getGlyph();
    public ParamsMatrix getMatrix();
    public ParamsTreeMap getTreeMap();
}
