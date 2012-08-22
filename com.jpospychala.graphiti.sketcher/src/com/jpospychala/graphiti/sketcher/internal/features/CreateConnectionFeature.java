package com.jpospychala.graphiti.sketcher.internal.features;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.algorithms.AlgorithmsFactory;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.Connection;

public class CreateConnectionFeature extends AbstractCreateConnectionFeature {

	private EClass clazz;
	
	public CreateConnectionFeature(IFeatureProvider fp, EClass clazz) {
		super(fp, clazz.getName(), "Create "+clazz.getName());
		this.clazz = clazz;
	}

	public boolean canCreate(ICreateConnectionContext context) {
		return true;
	}

	public Connection create(ICreateConnectionContext context) {
		return null;
	}

	public boolean canStartConnection(ICreateConnectionContext context) {
		return true;
	}

}
