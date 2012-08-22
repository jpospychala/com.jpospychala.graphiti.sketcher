package com.jpospychala.graphiti.sketcher.internal.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;

public class AddShapeFeature extends AbstractAddFeature {

	public AddShapeFeature(IFeatureProvider fp) {
		super(fp);
	}

	public boolean canAdd(IAddContext context) {
		return true;
	}

	public PictogramElement add(IAddContext context) {
		GraphicsAlgorithm ga = (GraphicsAlgorithm) context.getNewObject();
		
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		
		ContainerShape target = context.getTargetContainer();
		IGaService gaService = Graphiti.getGaService();
		
		ContainerShape container = peCreateService.createContainerShape(target , true);
		
		setDefaultAttributes(ga);
		container.setGraphicsAlgorithm(ga);
		
		int width = context.getWidth() > 0 ? context.getWidth() : 100;
		int height = context.getHeight() > 0 ? context.getHeight() : 100;
		gaService.setLocationAndSize(ga, context.getX(), context.getY(), width, height);
		
		peCreateService.createChopboxAnchor(container);

		link(container, ga);
		
		return container;
	}

	private void setDefaultAttributes(GraphicsAlgorithm ga) {
		ga.setLineStyle(LineStyle.SOLID);
		ga.setLineWidth(1);
		ga.setTransparency(0d);
		ga.setFilled(false);
	}

}
