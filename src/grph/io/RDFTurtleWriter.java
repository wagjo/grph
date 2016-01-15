/*
 * (C) Copyright 2009-2013 CNRS.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:

    Luc Hogie (CNRS, I3S laboratory, University of Nice-Sophia Antipolis) 
    Aurelien Lancin (Coati research team, Inria)
    Christian Glacet (LaBRi, Bordeaux)
    David Coudert (Coati research team, Inria)
    Fabien Crequis (Coati research team, Inria)
    Grégory Morel (Coati research team, Inria)
    Issam Tahiri (Coati research team, Inria)
    Julien Fighiera (Aoste research team, Inria)
    Laurent Viennot (Gang research-team, Inria)
    Michel Syska (I3S, University of Nice-Sophia Antipolis)
    Nathann Cohen (LRI, Saclay) 
 */
 
 package grph.io;

import grph.Grph;
import grph.properties.Property;

import java.io.IOException;
import java.io.PrintStream;

public class RDFTurtleWriter extends AbstractGraphTextWriter
{

    @Override
    public void printGraph(Grph g, PrintStream printStream) throws IOException
    {
	printStream.println("@prefix grph: <http://www-sop.inria.fr/members/Luc.Hogie/grph/>");
/*
	for (int v : g.getVertices().toIntArray())
	{
	    printStream.print("grph:node" + v);
	    printStream.print(" grph:colored ");

	    for (Property p : g.getProperties())
	    {
		if (p.getTarget() == TYPE.vertex)
		{

		}
	    }
	    printStream.print(g.getVertexColorProperty().getValue(v));
	    printStream.print(" .\n");
	}
*/
/*	for (int e : g.getEdges().toIntArray())
	{
	    int s = g.getOneVertex(e);
//	    printStream.append("grph:edge" + e);
	    printStream.append("_:b" + (e+1));
	    printStream.append(" grph:starts_from");
	    printStream.print(" grph:node" + s);
	    printStream.append(" .\n");

	    int t = g.getTheOtherVertex(e, s);
	    printStream.append("_:b" + (e+1));
	    printStream.append(" grph:heads_to");
	    printStream.print(" grph:node" + t);
	    printStream.append(" .\n");
	}*/

	for (int e : g.getEdges().toIntArray())
	{
	    int s = g.getOneVertex(e);
	    printStream.print("_:node" + s);
//	    printStream.append("grph:edge" + e);
	    printStream.append(" grph:edge");
	    int t = g.getTheOtherVertex(e, s);
	    printStream.print(" _:node" + t);
	    printStream.append(" .\n");
	}
}

    private void print(int element, Property p, PrintStream printStream)
    {
	String value = p.getValueAsString(element);

	if (value != null)
	{
	    printStream.print(element);
	    printStream.print(' ');
	    printStream.print("grph:" + p.getName());
	    printStream.print(' ');
	    printStream.print(value);
	    printStream.print(' ');
	    printStream.print('.');
	    printStream.print('\n');
	}
    }

    
    public static void main(String[] args)
    {
	Grph g = new grph.in_memory.InMemoryGrph();
	g.addNVertices(3);
	g.dring();	System.out.println(new RDFTurtleWriter().printGraph(g));
    }

}
