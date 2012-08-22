package com.jpospychala.graphiti.sketcher.internal;

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;


public class DiagramTypeProvider extends AbstractDiagramTypeProvider {

	public DiagramTypeProvider() {
		super();
		setFeatureProvider(new SketcherFeatureProvider(this));
	}
}
