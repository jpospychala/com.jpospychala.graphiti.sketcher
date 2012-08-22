package com.jpospychala.graphiti.sketcher.internal.features;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.algorithms.AlgorithmsFactory;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.algorithms.styles.StylesFactory;

import com.jpospychala.graphiti.sketcher.Activator;

public class CreateShapeFeature extends AbstractCreateFeature {

	public static final String GA_CLASS = Activator.PLUGIN_ID + ".clazz";
	private EClass clazz;

	public CreateShapeFeature(IFeatureProvider fp,
			EClass clazz) {
		super(fp, clazz.getName(), "Create "+clazz.getName());
		this.clazz = clazz;
	}

	public boolean canCreate(ICreateContext context) {
		return true;
	}

	public Object[] create(ICreateContext context) {
		GraphicsAlgorithm ga = (GraphicsAlgorithm) AlgorithmsFactory.eINSTANCE.create(clazz);
		if (ga instanceof Polygon) {
			Point p = StylesFactory.eINSTANCE.createPoint();
			p.setX(context.getX());
			p.setY(context.getY());
			((Polygon) ga).getPoints().add(p);
			p = StylesFactory.eINSTANCE.createPoint();
			p.setX(context.getX() + context.getWidth());
			p.setY(context.getY() + context.getHeight());
			((Polygon) ga).getPoints().add(p);
			
		} else if (ga instanceof Polyline) {
			Point p = StylesFactory.eINSTANCE.createPoint();
			p.setX(10);
			p.setY(10);
			((Polyline) ga).getPoints().add(p);
			p = StylesFactory.eINSTANCE.createPoint();
			p.setX(100);
			p.setY(100);
			((Polyline) ga).getPoints().add(p);
		}
		
		getDiagram().eResource().getContents().add(ga);
		addGraphicalRepresentation(context, ga);
		return new Object[] { ga };
	}

}
