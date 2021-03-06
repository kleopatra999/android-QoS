/*
 * TeleStax, Open Source Cloud Communications
 * Copyright 2011-2016, Telestax Inc and individual contributors
 * by the @authors tag.
 *
 * This program is free software: you can redistribute it and/or modify
 * under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 * For questions related to commercial use licensing, please contact sales@telestax.com.
 *
 */

package org.restcomm.app.utillib.CoverageSamples;

import java.util.ArrayList;

/**
 * Created by bscheurman on 16-03-21.
 */
public class CoverageSamplesSend {
    private long startTime = 0l;
    private String aheader;
    public ArrayList<CellSamplesSend> cells = new ArrayList<CellSamplesSend>();
    public CoverageSamplesSend (CoverageSamples cov)
    {
        int i,j;
        for (i=0; i<cov.cells.size(); i++)
            cells.add(new CellSamplesSend(cov.cells.get(i)));
        startTime = cov.startTime;
        aheader = cov.aheader;
    }

    public long getStartTime () {return startTime;}
    public void setStartTime (long value) { startTime = value;}
    public String getHeader () {return aheader;}
    public void setHeader (String value) { aheader = value;}
}
