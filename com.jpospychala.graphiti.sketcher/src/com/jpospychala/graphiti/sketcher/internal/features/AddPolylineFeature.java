package com.jpospychala.graphiti.sketcher.internal.features;

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.algorithms.styles.StylesFactory;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;

public class AddPolylineFeature extends AbstractAddFeature {

	private static final String TODO = "todo";
	private static final String UPDATELASTPOINT = "updatelastpoint";
	private static final String ADDPOINT = "addpoint";
	private MouseAdapter clicklistener;
	private MouseMoveListener movelistener;
	private Polyline ga;

	public AddPolylineFeature(IFeatureProvider fp) {
		super(fp);
	}

	public boolean canAdd(IAddContext context) {
		return true;
	}

	public PictogramElement add(IAddContext context) {
		ga = (Polyline) context.getNewObject();
		
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		
		ContainerShape target = context.getTargetContainer();
		
		Object todo = context.getProperty(TODO);
		if (todo == null) {
			ContainerShape container = peCreateService.createContainerShape(target , true);
			
			setDefaultAttributes(ga);
			container.setGraphicsAlgorithm(ga);
			ga.setFilled(true);
			peCreateService.createChopboxAnchor(container);
			link(container, ga);
		
			final DiagramEditor editor = (DiagramEditor)(getFeatureProvider().getDiagramTypeProvider().getDiagramEditor());
			final GraphicalViewer viewer = editor.getGraphicalViewer();
			
			if (clicklistener == null) {
				clicklistener = new MouseAdapter() {
	
					public void mouseUp(MouseEvent e) {
						IFeature feature = new AddPolylineFeature(getFeatureProvider());
						AddContext context = new AddContext();
						context.setNewObject(ga);
						context.putProperty(TODO, ADDPOINT);
						
						org.eclipse.draw2d.geometry.Point mousep = new org.eclipse.draw2d.geometry.Point();
						mousep.x = e.x;
						mousep.y = e.y;
						mousep = editor.calculateRealMouseLocation(mousep);
						context.setX(-ga.getX() -5 + mousep.x);
						context.setY(-ga.getY() + mousep.y);
						context.setTargetContainer(getDiagram());
						editor.executeFeature(feature, context);
					}
					
					@Override
					public void mouseDoubleClick(MouseEvent e) {
						viewer.getControl().removeMouseListener(clicklistener);
						viewer.getControl().removeMouseMoveListener(movelistener);
						clicklistener = null;
						movelistener = null;
					}
				};
				movelistener = new MouseMoveListener() {
					
					public void mouseMove(MouseEvent e) {
						IFeature feature = new AddPolylineFeature(getFeatureProvider());
						AddContext context = new AddContext();
						context.setNewObject(ga);
						context.putProperty(TODO, UPDATELASTPOINT);
						org.eclipse.draw2d.geometry.Point mousep = new org.eclipse.draw2d.geometry.Point();
						mousep.x = e.x;
						mousep.y = e.y;
						mousep = editor.calculateRealMouseLocation(mousep);
						context.setX(-ga.getX() -5 + mousep.x);
						context.setY(-ga.getY() + mousep.y);
						context.setTargetContainer(getDiagram());
						editor.executeFeature(feature, context);
					}
				};
				viewer.getControl().addMouseListener(clicklistener);
				viewer.getControl().addMouseMoveListener(movelistener);
			}

			return container;
		} else if (ADDPOINT.equals(todo)) {
			ContainerShape container = (ContainerShape) getFeatureProvider().getPictogramElementForBusinessObject(ga);
			
			
			Point p = StylesFactory.eINSTANCE.createPoint();
			p.setX(context.getX());
			p.setY(context.getY());
			ga.getPoints().add(p);
			
			return container;
		} else if (UPDATELASTPOINT.equals(todo)) {
			ContainerShape container = (ContainerShape) getFeatureProvider().getPictogramElementForBusinessObject(ga);
			
			if (ga.getPoints().size() > 1) {
				Point p = ga.getPoints().get(ga.getPoints().size() - 1);
				p.setX(context.getX());
				p.setY(context.getY());
			}
			
			return container;
		}
		
		throw new IllegalArgumentException();
	}

	private void setDefaultAttributes(GraphicsAlgorithm ga) {
		ga.setLineStyle(LineStyle.SOLID);
		ga.setLineWidth(1);
		ga.setTransparency(0d);
		ga.setFilled(false);
	}

}
