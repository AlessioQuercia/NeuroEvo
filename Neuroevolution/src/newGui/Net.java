package newGui;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import jGraph.Edge;
import jGraph.Structure;
import jGraph.Vertex;
import jGraph.chartXY;
import jGraph.code;
import jNeatCommon.CodeConstant;
import jneat.Genome;
import jneat.Organism;

public class Net extends JPanel
{
	private JFrame frame;
	
	public Net(JFrame frame) 
	{
		this.frame = frame;
		
		init();
	}

	private void init() 
	{
		setLayout(new GridBagLayout());
		
		setBorder(BorderFactory.createTitledBorder("Current net"));
	}
	
	   public void drawGraph(Organism o, chartXY graph) 
	  {
		 Genome _g1 = o.genome;
	  
		 Vector v1 = new Vector(1, 0);
		 Structure sx = new Structure();
	  
		 graph.initAzioni();
	  
		 sx.LoadGenome(_g1);
		 sx.generate_Grafo();
	  
		 sx.compute_Coordinate(getWidth(), getHeight());
		 
		 Iterator itr_point = sx.vVertex.iterator();
		 while (itr_point.hasNext()) 
		 {
			Vertex _point = ((Vertex) itr_point.next());
		 
			if ((_point.x) != 0 && (_point.y != 0) && (_point.is_real()))
			   v1.add(new code(_point, CodeConstant.NODO_N));
			if ((_point.x) != 0 && (_point.y != 0) && (_point.is_recurrent()))
			   v1.add(new code(_point, CodeConstant.NODO_R));
		 
		 }
	  
	  // store edge for interpreter
		 Iterator itr_edge = sx.vEdge.iterator();
		 while (itr_edge.hasNext()) 
		 {
			Edge _edge = ((Edge) itr_edge.next());
			Vertex _inode = _edge.in_node;
			Vertex _onode = _edge.out_node;
			int type = _edge.type;
		 
			double weight_edge = _edge.weight;
			int sign_edge;
			if (weight_edge >= 0 )
			   sign_edge = +1;
			else
			   sign_edge = -1;
		 
		 
		 
			if ((_inode.x) != 0
			&& (_inode.y != 0)
			&& (_onode.x) != 0
			&& (_onode.y != 0)
			&& (_edge.active)) 
			{
			   int x1 = _inode.x;
			   int y1 = _inode.y;
			   int x2 = _onode.x;
			   int y2 = _onode.y;
			   v1.add(new code(_inode, _onode, type, sign_edge));
			}
		 }
	  
		 graph.setScale((getWidth())/2 , getHeight());
		 graph.setAxis(false);
		 graph.setGrid(false);
		 graph.setGrafo(v1);
		 graph.repaint();
		 
			GridBagConstraints gc = new GridBagConstraints();
			
			gc.anchor = GridBagConstraints.LINE_START;
			gc.fill = GridBagConstraints.BOTH;
			gc.weightx = 0.5;
			gc.weighty = 0.5;
			
			gc.gridx = 0;
			gc.gridy = 0;	
			add(graph, gc);
	  }
	
}
