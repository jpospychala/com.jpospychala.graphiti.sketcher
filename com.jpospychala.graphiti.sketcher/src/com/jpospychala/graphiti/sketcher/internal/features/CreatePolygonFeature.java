package com.jpospychala.graphiti.sketcher.internal.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.algorithms.AlgorithmsFactory;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.algorithms.styles.StylesFactory;

public class CreatePolygonFeature extends AbstractCreateFeature {

	public CreatePolygonFeature(IFeatureProvider fp) {
		super(fp, "Polygon", "Create Polygon");
	}

	public boolean canCreate(ICreateContext context) {
		return true;
	}

	public Object[] create(ICreateContext context) {
		Polyline ga = (Polyline) AlgorithmsFactory.eINSTANCE.createPolygon();
		getDiagram().eResource().getContents().add(ga);
		
		Point p = StylesFactory.eINSTANCE.createPoint();
		p.setX(context.getX());
		p.setY(context.getY());
		ga.getPoints().add(p);
		
		//p = StylesFactory.eINSTANCE.createPoint();
		//p.setX(context.getX() + 100);
		//p.setY(context.getY() + 100);
		//ga.getPoints().add(p);
		
		addGraphicalRepresentation(context, ga);
		
		return new Object[] { ga };
	}

}
