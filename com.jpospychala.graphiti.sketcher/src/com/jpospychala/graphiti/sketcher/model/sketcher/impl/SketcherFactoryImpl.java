/**
 */
package com.jpospychala.graphiti.sketcher.model.sketcher.impl;

import com.jpospychala.graphiti.sketcher.model.sketcher.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SketcherFactoryImpl extends EFactoryImpl implements SketcherFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SketcherFactory init() {
		try {
			SketcherFactory theSketcherFactory = (SketcherFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.jpospychala.com/sketcher/1.0"); 
			if (theSketcherFactory != null) {
				return theSketcherFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SketcherFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SketcherFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case SketcherPackage.MODEL_OBJECT: return createModelObject();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelObject createModelObject() {
		ModelObjectImpl modelObject = new ModelObjectImpl();
		return modelObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SketcherPackage getSketcherPackage() {
		return (SketcherPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SketcherPackage getPackage() {
		return SketcherPackage.eINSTANCE;
	}

} //SketcherFactoryImpl
