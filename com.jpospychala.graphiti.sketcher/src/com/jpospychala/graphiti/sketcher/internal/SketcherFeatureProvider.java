package com.jpospychala.graphiti.sketcher.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;

import com.jpospychala.graphiti.sketcher.internal.features.AddPolylineFeature;
import com.jpospychala.graphiti.sketcher.internal.features.AddShapeFeature;
import com.jpospychala.graphiti.sketcher.internal.features.CreatePolygonFeature;
import com.jpospychala.graphiti.sketcher.internal.features.CreatePolylineFeature;
import com.jpospychala.graphiti.sketcher.internal.features.CreateShapeFeature;

public class SketcherFeatureProvider extends DefaultFeatureProvider {

	public SketcherFeatureProvider(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}

	@Override
	public ICreateFeature[] getCreateFeatures() {
		EList<EClassifier> classifiers = AlgorithmsPackage.eINSTANCE.getEClassifiers();
		List<ICreateFeature> createFeatures = new ArrayList<ICreateFeature>();
		for (EClassifier c : classifiers) {
			if (c instanceof EClass) {
				EClass cl = (EClass) c;
				if ((! cl.isAbstract()) && (! AlgorithmsPackage.eINSTANCE.getPolyline().isSuperTypeOf(cl))) {
					createFeatures.add(new CreateShapeFeature(this, cl));
				}
			}
		}
		
		createFeatures.add(new CreatePolylineFeature(this));
		createFeatures.add(new CreatePolygonFeature(this));
		
		return createFeatures.toArray(new ICreateFeature[createFeatures.size()]);
	}
	
	@Override
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		return super.getCreateConnectionFeatures();
	}
	
	@Override
	public IAddFeature getAddFeature(IAddContext context) {
		Object newObj = context.getNewObject();
		if (newObj instanceof Polyline) {
			return new AddPolylineFeature(this);
		}
		return new AddShapeFeature(this);
	}

}
